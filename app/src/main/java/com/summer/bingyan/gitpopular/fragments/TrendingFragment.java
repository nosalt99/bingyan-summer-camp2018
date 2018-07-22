package com.summer.bingyan.gitpopular.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.summer.bingyan.gitpopular.R;
import com.summer.bingyan.gitpopular.activities.BottomTabActivity;
import com.summer.bingyan.gitpopular.adapters.TrendingAdapter;
import com.summer.bingyan.gitpopular.data.Trend;
import com.summer.bingyan.gitpopular.presenter.TrendingPresenter;

import java.util.ArrayList;
import java.util.List;

public class TrendingFragment extends Fragment {
    private TrendingAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Trend>trends=new ArrayList<>();
    private String url="https://github.com/trending?since=daily";
    private String suburl;
    private String suburl2,suburl3;
    private final static String TAG="luchixiang";
    private SwipeRefreshLayout swipeRefreshLayout;
    private  TrendingPresenter trendingPresenter;
    private Toolbar toolbar;
    public TrendingFragment() {
        // Required empty public constructor
    }
    public static TrendingFragment newInstance() {
        TrendingFragment fragment = new TrendingFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Context contexttheme=new ContextThemeWrapper(getActivity(),R.style.Blue);
       switch (SettingFragment.theme_selected)
        {
            case 0:
            contexttheme=new ContextThemeWrapper(getActivity(),R.style.Red);
            break;
            case 1:
                contexttheme=new ContextThemeWrapper(getActivity(),R.style.Blue);
                break;
            case 2:
                contexttheme=new ContextThemeWrapper(getActivity(),R.style.green);
                break;
            case 3:
                contexttheme=new ContextThemeWrapper(getActivity(),R.style.Purple);
                break;
            case 4:
                contexttheme=new ContextThemeWrapper(getActivity(),R.style.Cyan);
                break;
            case 5:
                contexttheme=new ContextThemeWrapper(getActivity(),R.style.Yellow);
        }
        LayoutInflater themeinflater=inflater.cloneInContext(contexttheme);
        View view=themeinflater.inflate(R.layout.fragment_trending, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.show_trending);
        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_red_light, android.R.color.holo_green_light,
                android.R.color.holo_blue_bright, android.R.color.holo_orange_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        trendingPresenter.getTrending(url);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },1000);
            }
        });
        layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        Context context=getActivity();
        adapter = new TrendingAdapter(context,recyclerView,trends);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        trendingPresenter=new TrendingPresenter(context,this);
        trendingPresenter.getTrending("https://github.com/trending");
        toolbar=(Toolbar)view.findViewById(R.id.trend_toolbar);
        toolbar.setTitle("Trending");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        return view;
    }
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    public void notifyChange(List<Trend> list){
        adapter.trendListChanged(list);
        adapter.notifyDataSetChanged();
        Log.d(TAG, "dataset:"+"true");
    }
    public void showToast()
    {
        Toast.makeText(getActivity(),"网络连接失败",Toast.LENGTH_LONG).show();
    }
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.trend_menu,menu);
        Log.d(TAG, "onCreateOptionsMenu: "+"assadas");
        super.onCreateOptionsMenu(menu, inflater);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int i=url.indexOf("?");
        suburl=url.substring(0,i+7);
        int j=url.indexOf(".com/");
        suburl2=url.substring(0,j+13);
        suburl3=url.substring(i,url.length());
        switch (item.getItemId()) {
            case R.id.week:
                url=suburl+"weekly";
                trendingPresenter.getTrending(url);
                Toast.makeText(getActivity(),"change to week",Toast.LENGTH_LONG).show();
                return true;
            case R.id.today:
                Toast.makeText(getActivity(),"change to today",Toast.LENGTH_LONG).show();
                url=suburl+"daily";
                Log.d(TAG, "onOptionsItemSelected: "+url);
                trendingPresenter.getTrending(url);
                return true;
            case R.id.month:
                Toast.makeText(getActivity(),"change to month",Toast.LENGTH_LONG).show();
                url=suburl+"monthly";
                trendingPresenter.getTrending(url);
                return true;
            case R.id.all:
                url=suburl2+suburl3;
                    Toast.makeText(getActivity(),"change to all",Toast.LENGTH_LONG).show();
                    trendingPresenter.getTrending(url);
                    return true;
            case R.id.java:
                url=suburl2+"/java"+suburl3;
                Toast.makeText(getActivity(),"change to java",Toast.LENGTH_LONG).show();
                trendingPresenter.getTrending(url);
                return true;
            case R.id.c:
                url=suburl2+"/c%23"+suburl3;
                Toast.makeText(getActivity(),"change to c#",Toast.LENGTH_LONG).show();
                trendingPresenter.getTrending(url);
                return true;
            case R.id.cjiajia:
                url=suburl2+"/c++"+suburl3;
                Toast.makeText(getActivity(),"change to c++",Toast.LENGTH_LONG).show();
                trendingPresenter.getTrending(url);
                return true;
            case R.id.javascript:
                url=suburl2+"/javascript"+suburl3;
                Toast.makeText(getActivity(),"change to javascript",Toast.LENGTH_LONG).show();
                trendingPresenter.getTrending(url);
                return true;
            case R.id.css:
                url=suburl2+"/css"+suburl3;
                Toast.makeText(getActivity(),"change to css",Toast.LENGTH_LONG).show();
                trendingPresenter.getTrending(url);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
