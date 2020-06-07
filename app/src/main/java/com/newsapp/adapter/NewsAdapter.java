package com.newsapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.newsapp.R;
import com.newsapp.activity.NewsDetailActivity;
import com.newsapp.model.HomepageModel;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<HomepageModel.News> news;
    int image_left = 1;
    int image_top = 2;

    public NewsAdapter(Context context, List<HomepageModel.News> news) {

        this.context = context;
        this.news = news;

    }

    @Override
    public int getItemViewType(int position) {
        if ((position + 1) % 8 == 0 || position == 0) {
            return image_top;
        } else {
            return image_left;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == image_left) {
            View v = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
            return new ViewHolder(v);
        } else {
            View v = LayoutInflater.from(context).inflate(R.layout.item_news_image, parent, false);
            return new ViewHolder(v);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HomepageModel.News singleNews = news.get(holder.getAdapterPosition());
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.newsTitle.setText(removeHtml(singleNews.getTitle()).trim());
        viewHolder.newsDescp.setText(removeHtml(singleNews.getPostContent()).trim());
        if (singleNews.getSource() != null) {
            viewHolder.newsSource.setText(singleNews.getSource());
        }
        if(singleNews.getImage().length() <=1){
            viewHolder.newsImage.setVisibility(View.GONE);

        }else{
            viewHolder.newsImage.setVisibility(View.VISIBLE);
            Glide.with(context).load(singleNews.getImage()).into(viewHolder.newsImage);

        }


    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        View holder;
        ImageView newsImage;
        TextView newsTitle, newsDescp, newsDate, newsSource, newsView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            holder = itemView;
            newsImage = holder.findViewById(R.id.news_image);
            newsTitle = holder.findViewById(R.id.news_title);
            newsDescp = holder.findViewById(R.id.news_descp);
            newsDate = holder.findViewById(R.id.news_date);
            newsView = holder.findViewById(R.id.news_view);
            newsSource = holder.findViewById(R.id.news_source);
            holder.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(context, NewsDetailActivity.class);
            intent.putExtra("pid",news.get(getAdapterPosition()).getPid());
            context.startActivity(intent);
        }
    }
    public static String removeHtml(String html) {
        html = html.replaceAll("<(.*?)\\>", " ");
        html = html.replaceAll("<(.*?)\\\n", " ");
        html = html.replaceFirst("(.*?)\\>", " ");
        html = html.replaceAll("&nbsp;", " ");
        html = html.replaceAll("&amp;", " ");
        return html;
    }

}
