package com.summer.bingyan.gitpopular.model;

import android.os.AsyncTask;

import com.summer.bingyan.gitpopular.data.Trend;

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
    private Trend trend;
    private List<Trend> trends=new ArrayList<>();
    int length;
    public void getTrending(final String url,final TrendingCallback callback){
        start(url,callback);
    }
    public void start( final String url,final TrendingCallback callback)
    {
        new Thread(){
            public void run()
            {
                try {
                   document = Jsoup.connect(url).get();
                   elements=document.select(".col-12 d-block width-full py-4 border-bottom");
                    length=elements.size();
                    for (int k=0;k<10&&k<length;k++)
                    {
                        Element element1=elements.get(k);
                        Element element2=element1.select(".d-inline-block col-9 mb-1").first();
                        urlOfTitle=element2.select("a").toString();//不确定这么写对不对
                        trend.setUrl("https://github.com"+urlOfTitle);
                        title=urlOfTitle.substring(1,urlOfTitle.length());
                        trend.setTitle(title);
                        content=element1.select(".col-9 d-inline-block text-gray m-0 pr-4").text();
                        trend.setContent(content);
                        trends.add(trend);
                    }
                    callback.onSuccess(trends);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
    }
public interface TrendingCallback{
       void onSuccess(List list);
}

}
