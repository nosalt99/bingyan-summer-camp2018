package com.summer.bingyan.gitpopular.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.summer.bingyan.gitpopular.data.Popular;
import com.summer.bingyan.gitpopular.data.Trend;
import com.summer.bingyan.gitpopular.utils.MyDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class FavoriteModel {
    private List<Trend>trends=new ArrayList<>();
    private List<Popular>populars=new ArrayList<>();
    private FavoriteCallback favoriteCallback;
    private MyDatabaseHelper myDatabaseHelper;
    public FavoriteModel(FavoriteCallback favoriteCallback,MyDatabaseHelper myDatabaseHelper)
    {
        this.favoriteCallback=favoriteCallback;
        this.myDatabaseHelper=myDatabaseHelper;
    }
    public void query_trend()
    {
        SQLiteDatabase db=myDatabaseHelper.getWritableDatabase();
        Cursor cursor=db.query("Trend",null,null,null,null,null,null);
        if (cursor.moveToFirst())
        {
            do {
                Trend trend=new Trend();
                trend.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                trend.setContent(cursor.getString(cursor.getColumnIndex("content")));
                Log.d("luchixiang", "query: "+trend.getTitle());
                trends.add(trend);
            }while (cursor.moveToNext());
        }
        cursor.close();
        favoriteCallback.onSuccess(trends);
    }
    public void query_popular()
    {
        SQLiteDatabase db=myDatabaseHelper.getWritableDatabase();
        Cursor cursor=db.query("Popular",null,null,null,null,null,null);
        if (cursor.moveToFirst())
        {
            do {
                Popular popular=new Popular();
                popular.setFull_name(cursor.getString(cursor.getColumnIndex("title")));
                popular.setDescription(cursor.getString(cursor.getColumnIndex("content")));
                populars.add(popular);
            }while (cursor.moveToNext());
        }
        cursor.close();
        favoriteCallback.onSuccess(populars);
    }
    public interface FavoriteCallback{
        void onSuccess(List list);
    }
}
