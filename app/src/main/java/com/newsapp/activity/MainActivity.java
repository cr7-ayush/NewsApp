package com.newsapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.slidertypes.BaseSliderView;
import com.glide.slider.library.slidertypes.DefaultSliderView;


import com.newsapp.R;
import com.newsapp.adapter.GridAdapter;
import com.newsapp.adapter.NewsAdapter;
import com.newsapp.model.HomepageModel;
import com.newsapp.rest.ApiClient;
import com.newsapp.rest.ApiInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    SliderLayout sliderLayout;
    Toolbar toolbar;
    GridView gridView;
    GridAdapter gridAdapter;
    RecyclerView recyclerView;
    NewsAdapter newsAdapter;
    List<HomepageModel.News> news;
    List<HomepageModel.CategoryBotton> categoryBottons;

    int posts = 5;
    int page = 1;
    boolean isFromStart = true;
    ProgressBar progressBar;

    NestedScrollView nestedScrollView;

    SwipeRefreshLayout swipeRefreshLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intiViews();

        page=1;
        isFromStart=true;



        getHomeData();

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if(scrollY==(v.getChildAt(0).getMeasuredHeight()-v.getMeasuredHeight())){
                    isFromStart=false;
                    progressBar.setVisibility(View.VISIBLE);
                    page++;
                    getHomeData();
                }
            }
        });

    }

    private void getHomeData() {

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Map<String,String> params= new HashMap<>();
        params.put("page",page+"");
        params.put("posts",posts+"");
        Call<HomepageModel> call=apiInterface.getHomepageApi(params);
        call.enqueue(new Callback<HomepageModel>() {


            @Override
            public void onResponse(Call<HomepageModel> call, Response<HomepageModel> response) {

                updateDataToHomepage(response.body());

            }

            @Override
            public void onFailure(Call<HomepageModel> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);

            }
        });

    }

    private void updateDataToHomepage(HomepageModel body) {
        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
        if(isFromStart){
            news.clear();
            categoryBottons.clear();
        }
        for(int i = 0 ; i< body.getBanners().size(); i++ ){
            DefaultSliderView defaultSliderView = new DefaultSliderView( this);
            defaultSliderView.setRequestOption(new RequestOptions().centerCrop());
            defaultSliderView.image(body.getBanners().get(i).getImage());
            defaultSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(BaseSliderView slider) {
                    //TODO: handling click on image

                }
            });
            sliderLayout.addSlider(defaultSliderView);
        }
        sliderLayout.startAutoCycle();
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Stack);
        sliderLayout.setDuration(4000);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);

        int beforeNewsSize=news.size();
        for(int i=0;i<body.getNews().size();i++){
            news.add(body.getNews().get(i));

        }
        categoryBottons.addAll(body.getCategoryBotton());

        if(isFromStart){
            recyclerView.setAdapter(newsAdapter);
            gridView.setAdapter(gridAdapter);
        }else{
            newsAdapter.notifyItemRangeInserted(beforeNewsSize,body.getNews().size());
        }






    }

    private void intiViews() {
        sliderLayout = findViewById(R.id.slider);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressBar=findViewById(R.id.progressbar);
        nestedScrollView=findViewById(R.id.nested);

        categoryBottons = new ArrayList<>();

        gridView = findViewById(R.id.gird_view);
        gridAdapter=new GridAdapter(this,categoryBottons);




        recyclerView=findViewById(R.id.recy_news);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setNestedScrollingEnabled(false);

        news= new ArrayList<>();
        newsAdapter=new NewsAdapter(this,news);

        swipeRefreshLayout=findViewById(R.id.swipe);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setColorSchemeResources(R.color.orange,R.color.blue,R.color.green);
        swipeRefreshLayout.setOnRefreshListener(this);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,CategoryActivity.class);
                intent.putExtra("cid",categoryBottons.get(position).getCid());
                intent.putExtra("cname",categoryBottons.get(position).getName());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        sliderLayout.stopAutoCycle();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.homepage_toolbar_menu,menu);
       return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.video){
           startActivity(new Intent(this,YoutubeActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        isFromStart=true;
        page=1;
        getHomeData();
    }
}