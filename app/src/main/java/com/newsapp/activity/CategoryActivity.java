package com.newsapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.newsapp.R;
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

public class CategoryActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView recyclerView;
    Toolbar toolbar;
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressBar progressBar;

    int page = 1;
    int posts = 2;

    NewsAdapter newsAdapter;
    List<HomepageModel.News> news=new ArrayList<>();

    boolean isStartFirst=true , shouldFetchData = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        recyclerView=findViewById(R.id.news_recy);
        toolbar=findViewById(R.id.toolbar);
        swipeRefreshLayout=findViewById(R.id.swipe);
        progressBar=findViewById(R.id.progressbar);

        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(this);

        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.icon_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });
        toolbar.setTitleTextColor(Color.WHITE);

        toolbar.setTitle(getIntent().getStringExtra("cname"));

        newsAdapter=new NewsAdapter(this,news);

        isStartFirst=true;
        page=1;

        getCategoryData();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                int visibleItemCount = linearLayoutManager.getChildCount();
                int totalItemCount = linearLayoutManager.getItemCount();
                int passVisbleCount= linearLayoutManager.findFirstCompletelyVisibleItemPosition();

                if((passVisbleCount+visibleItemCount)==totalItemCount){
                    if(shouldFetchData){
                        shouldFetchData=false;
                        isStartFirst=false;
                        progressBar.setVisibility(View.VISIBLE);
                        page++;
                        getCategoryData();
                    }
                }
            }
        });
    }



    private void getCategoryData() {

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Map<String,String> params= new HashMap<>();
        params.put("page",page+"");
        params.put("posts",posts+"");
        params.put("cid",getIntent().getIntExtra("cid",0)+"");
        Call<HomepageModel> call=apiInterface.getNewsByCId(params);
        call.enqueue(new Callback<HomepageModel>() {


            @Override
            public void onResponse(Call<HomepageModel> call, Response<HomepageModel> response) {

                swipeRefreshLayout.setRefreshing(false);
                progressBar.setVisibility(View.GONE);

                int beforeSize=news.size();
                if(isStartFirst){
                    news.clear();
                }
                news.addAll(response.body().getNews());

                if(isStartFirst){
                    recyclerView.setAdapter(newsAdapter);
                }else {
                    newsAdapter.notifyItemRangeInserted(beforeSize,response.body().getNews().size());
                }

                shouldFetchData=true;


            }

            @Override
            public void onFailure(Call<HomepageModel> call, Throwable t) {
                Toast.makeText(CategoryActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);

            }
        });


    }

    @Override
    public void onRefresh() {
        isStartFirst=true;
        page=1;
        shouldFetchData=true;
        getCategoryData();
    }
}