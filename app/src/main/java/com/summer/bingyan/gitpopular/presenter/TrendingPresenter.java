package com.summer.bingyan.gitpopular.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.summer.bingyan.gitpopular.data.Trend;
import com.summer.bingyan.gitpopular.fragments.TrendingFragment;
import com.summer.bingyan.gitpopular.model.TrendingModel;

import java.util.ArrayList;
import java.util.List;

public class TrendingPresenter {
    private TrendingFragment trendingFragment;
    private TrendingModel trendingModel;
    private Context context;
    private List<Trend> trends=new ArrayList<>();
    public TrendingPresenter(Context context, TrendingFragment trendingFragment){
        this.trendingFragment=trendingFragment;
        this.context=context;
    }
    public void getTrending(String url){
       trendingModel=new TrendingModel(context,new TrendingModel.TrendingCallback() {
           @Override
           public void onSuccess(List list) {
               trends.clear();
               trends.addAll(list);
               trendingFragment.notifyChange(trends);
           }
           public void onFail()
           {
               trendingFragment.showToast();
           }
       });
       trendingModel.getTrending(url);
    }
}
