package com.summer.bingyan.gitpopular.model;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.summer.bingyan.gitpopular.data.Popular;
import com.summer.bingyan.gitpopular.data.owner;
import com.summer.bingyan.gitpopular.utils.MyDatabaseHelper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PopularModel {
    private PopularCallback popularCallback;
    private Context context;
    private List<Popular> populars=new ArrayList<>();
    private List<Popular>populars_fa=new ArrayList<>();
    private final static int UPDATE=0x1;
    private final static int FAIL=0x2;
    private final static String TAG="luchixiang";
    private FavoriteModel favoriteModel;
    public PopularModel(Context context,PopularCallback popularCallback)
    {
        this.popularCallback=popularCallback;
        this.context=context;
        MyDatabaseHelper myDatabaseHelper=new MyDatabaseHelper(context,"Git.db",null,2);
        favoriteModel=new FavoriteModel(new FavoriteModel.FavoriteCallback() {
            @Override
            public void onSuccess(List list) {
                populars_fa.addAll(list);
            }
        },myDatabaseHelper);
    }
    public void getPopular(final String url)//json字符串
    {
        start(url);
    }
    private Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what)
            {
                case UPDATE:
                    popularCallback.onSuccess(populars);
                    break;
                case FAIL:
                    popularCallback.onFail();
            }
            return false;
        }
    });
    public void start(final String url)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                favoriteModel.query_popular();
                String response=sendRequest(url);
                Gson gson=new Gson();
                JsonParser parser=new JsonParser();
                JsonElement rootElement=parser.parse(response);
                JsonObject jsonObject2=rootElement.getAsJsonObject();
                JsonArray jsonArray=jsonObject2.get("items").getAsJsonArray();
                for (JsonElement jsonElement:jsonArray)
                {
                    JsonObject jsonObject=jsonElement.getAsJsonObject();
                    String full_name=jsonObject.get("full_name").getAsString();
                    long id=jsonObject.get("id").getAsLong();
                    String html_url=jsonObject.get("html_url").getAsString();
                    String description;
                    if(jsonObject.get("description").isJsonNull())
                    {
                        description="";
                   }
                    else description=jsonObject.get("description").getAsString();
                    Log.d(TAG, "rundebug: "+description);
                    owner owner=new owner();
                    JsonObject jsonObject1=jsonObject.get("owner").getAsJsonObject();
                    long id_owner=jsonObject1.get("id").getAsLong();
                    String avatar_url=jsonObject1.get("avatar_url").getAsString();
                    owner.setAvatar_url(avatar_url);
                    owner.setId(id_owner);
                    Popular popular=new Popular();
                    popular.setFull_name(full_name);
                    popular.setHtml_url(html_url);
                    popular.setId(id);
                    popular.setDescription(description);
                    popular.setOwner(owner);
                    for (Popular popular1:populars_fa)
                    {
                        if(full_name.equals(popular1.getFull_name()))
                        {
                            popular.setFavorite(true);
                        }
                    }
                    populars.add(popular);
                }
                Message message=new Message();
                message.what=UPDATE;
                handler.sendMessage(message);
            }
        }).start();
    }
    public String sendRequest(String path)
    {
        HttpURLConnection httpURLConnection=null;
        StringBuilder stringBuilder=new StringBuilder();
        try {
            URL url=new URL(path);
            httpURLConnection=(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(900000);
            httpURLConnection.setReadTimeout(90000);
            InputStream in=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line=bufferedReader.readLine())!=null)
            {
                stringBuilder.append(line);
            }
    }catch (Exception e)
        {
            e.printStackTrace();
            Message message=new Message();
            message.what=FAIL;
            handler.sendMessage(message);
        }finally {
            if (httpURLConnection!=null)
            {
                httpURLConnection.disconnect();
            }
        }
        return stringBuilder.toString();
    }
   public interface PopularCallback{
        void onSuccess(List list);
        void onFail();
    }
}
