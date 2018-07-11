package com.summer.bingyan.gitpopular.presenter;

import android.support.v4.app.Fragment;

import com.summer.bingyan.gitpopular.model.TrendingModel;

import java.util.List;

public class TrendingPresenter {
   private Fragment fragment;
    private TrendingModel trendingModel;
    public TrendingPresenter(Fragment fragment){
        this.fragment=fragment;
    }
    public void getTrending(String url){
        trendingModel.getTrending(url, new TrendingModel.TrendingCallback() {
            @Override
            public void onSuccess(List list) {
                fragment.setAdapter(list);
            }
        });
    }

}
