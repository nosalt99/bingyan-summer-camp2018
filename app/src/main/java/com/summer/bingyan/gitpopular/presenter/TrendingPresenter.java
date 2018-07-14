package com.summer.bingyan.gitpopular.presenter;

import android.support.v4.app.Fragment;

import com.summer.bingyan.gitpopular.data.Trend;
import com.summer.bingyan.gitpopular.fragments.TrendingFragment;
import com.summer.bingyan.gitpopular.model.TrendingModel;

import java.util.ArrayList;
import java.util.List;

public class TrendingPresenter {
    private TrendingFragment trendingFragment;
    private TrendingModel trendingModel;
    private List<Trend> trends=new ArrayList<>();
    public TrendingPresenter(TrendingFragment trendingFragment){
        this.trendingFragment=trendingFragment;
    }
    public void getTrending(String url){
       trendingModel=new TrendingModel(new TrendingModel.TrendingCallback() {
           @Override
           public void onSuccess(List list) {
               trends.addAll(list);
               trendingFragment.setAdapter(trends);
           }
       });
       trendingModel.getTrending(url);
    }
}
