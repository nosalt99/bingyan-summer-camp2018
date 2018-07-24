package com.summer.bingyan.gitpopular.model;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.summer.bingyan.gitpopular.data.Trend;
import com.summer.bingyan.gitpopular.utils.MyDatabaseHelper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


public class TrendingModel {
    private Document document;
    private Elements elements;
    private String title;
    private String content;
    private String urlOfTitle;
    private TrendingCallback trendingCallback;
    private int length;
    private Context context;
    private List<Trend> trends=new ArrayList<>();
    private List<Trend> trends_fa=new ArrayList<>();
    private final static String TAG="luchixiang";
    private final static int UPDATE=0x1;
    private final static int FAIL=0x2;
    private FavoriteModel favoriteModel;
    public TrendingModel(Context context,TrendingCallback trendingCallback)
    {
        this.trendingCallback=trendingCallback;
        this.context=context;
        MyDatabaseHelper myDatabaseHelper=new MyDatabaseHelper(context,"Git.db",null,2);
        favoriteModel=new FavoriteModel(new FavoriteModel.FavoriteCallback() {
            @Override
            public void onSuccess(List list) {
                trends_fa.addAll(list);
            }
        },myDatabaseHelper);
    }
    public void getTrending(final String url){
        start(url);
    }
    private Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what)
            {
                case UPDATE:
                    trendingCallback.onSuccess(trends);
                    break;
                case FAIL:
                    trendingCallback.onFail();
            }
            return false;
        }
    });
    public void start( final String url)
    {
        trends.clear();
        favoriteModel.query_trend();
        Log.d(TAG, "start: "+url);
        new Thread(new Runnable() {
            public void run() {
                try {
                    document = Jsoup.connect(url).maxBodySize(0).timeout(800000000).get();
                    elements = document.select("li[class=col-12 d-block width-full py-4 border-bottom]");
                    length = elements.size();
                    for (int k = 0; k < length; k++) {
                        Element element1 = elements.get(k);
                        Element element2 = element1.select("div[class=d-inline-block col-9 mb-1]").first();
                        String reply = element2.select("a").toString();
                        int o=reply.indexOf("href=");
                        int j=reply.indexOf("> <span");
                        urlOfTitle=reply.substring(o+6,j-1);
                        Trend trend=new Trend();
                        trend.setUrl("https://github.com" + urlOfTitle);
                        title = urlOfTitle.substring(1, urlOfTitle.length());
                        trend.setTitle(title);
                        content = element1.select("p[class=col-9 d-inline-block text-gray m-0 pr-4]").text();
                        trend.setContent(content);
                        if(trends_fa.size()!=0)
                        {
                            for (Trend trend1:trends_fa)
                        {
                            if (trend.getTitle().equals(trend1.getTitle()))
                            {
                                trend.setFavorite(true);
                            }
                        }
                        }
                        trends.add(trend);
                    }
                    Message message=new Message();
                    message.what=UPDATE;
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                    Message message=new Message();
                    message.what=FAIL;
                    handler.sendMessage(message);
                }
            }
        }).start();
        }
public interface TrendingCallback{
       void onSuccess(List list);
       void onFail();
}
}
