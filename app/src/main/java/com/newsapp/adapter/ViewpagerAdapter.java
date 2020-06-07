package com.newsapp.adapter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.newsapp.fragment.FragmentYoutube;
import com.newsapp.model.OurYtModel;

public class ViewpagerAdapter extends FragmentPagerAdapter {

    Context context;
    OurYtModel ourYtModel;
    public ViewpagerAdapter(@NonNull FragmentManager fm, OurYtModel ourYtModel, Context context) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context=context;
        this.ourYtModel=ourYtModel;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Bundle bundle= new Bundle();
        bundle.putString("cid",ourYtModel.getYoutubeData().get(position).getChannelId());
        FragmentYoutube fragmentYoutube=new FragmentYoutube();
        fragmentYoutube.setArguments(bundle);
        return fragmentYoutube;
    }

    @Override
    public int getCount() {
        return ourYtModel.getYoutubeData().size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return  ourYtModel.getYoutubeData().get(position).getTitle();
    }
}
