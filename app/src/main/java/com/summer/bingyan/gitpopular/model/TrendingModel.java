package com.summer.bingyan.gitpopular.model;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class TrendingModel {
    private Document document;
    private Elements elements;
    int length;
    public void getTrending(final String url,final TrendingCallback callback){
        start(url);
    }
    public void start( final String url)
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

                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
    }
public interface TrendingCallback{
       void Onsuccess(String data);
}

}
