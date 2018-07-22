package com.summer.bingyan.gitpopular.presenter;

import android.content.Context;
import android.util.Log;

import com.summer.bingyan.gitpopular.data.Popular;
import com.summer.bingyan.gitpopular.data.Trend;
import com.summer.bingyan.gitpopular.fragments.FavoriteFragment;
import com.summer.bingyan.gitpopular.model.FavoriteModel;
import com.summer.bingyan.gitpopular.utils.MyDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class FavoritePresenter {
    private FavoriteFragment favoriteFragment;
    private List<Trend> trends = new ArrayList<>();
    private List<Popular>populars=new ArrayList<>();
    private MyDatabaseHelper myDatabaseHelper;
    private FavoriteModel favoriteModel;
    public FavoritePresenter(FavoriteFragment favoriteFragment,MyDatabaseHelper myDatabaseHelper)
    {
        this.favoriteFragment=favoriteFragment;
        this.myDatabaseHelper=myDatabaseHelper;
    }
    public  void query_trend()
    {
        favoriteModel=new FavoriteModel(new FavoriteModel.FavoriteCallback() {
            @Override
            public void onSuccess(List list) {
                trends.clear();
                trends.addAll(list);
                for (Trend trend:trends)
                {
                    trend.setFavorite(true);
                }
                favoriteFragment.favoritetrendListChanged(trends);
            }
        },myDatabaseHelper);
        favoriteModel.query_trend();
    }
    public void query_popular()
    {
        favoriteModel=new FavoriteModel(new FavoriteModel.FavoriteCallback() {
            @Override
            public void onSuccess(List list) {
                populars.clear();
                populars.addAll(list);
                for (Popular popular:populars)
                {
                    popular.setFavorite(true);
                }
                favoriteFragment.favoritepopularListChanged(populars);
            }
        },myDatabaseHelper);
        favoriteModel.query_popular();
    }
}
