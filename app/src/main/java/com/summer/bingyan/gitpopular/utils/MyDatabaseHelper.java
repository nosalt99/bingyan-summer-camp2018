package com.summer.bingyan.gitpopular.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_TREND="create table Trend("+"title text,"+"content text)";
    public static final String CREATE_POPULAR="create table Popular("+"title text,"+"content text)";
    private Context context;
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version)
    {
        super(context,name,factory,version);
        this.context=context;
    }
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TREND);
        db.execSQL(CREATE_POPULAR);
    }
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion)
    {
        db.execSQL("drop table if exists Trend");
        db.execSQL("drop table if exists Popular");
        onCreate(db);
    }
}
