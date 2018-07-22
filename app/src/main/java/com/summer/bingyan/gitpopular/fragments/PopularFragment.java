package com.summer.bingyan.gitpopular.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.summer.bingyan.gitpopular.R;
import com.summer.bingyan.gitpopular.adapters.PopularAdapter;
import com.summer.bingyan.gitpopular.data.Popular;
import com.summer.bingyan.gitpopular.presenter.PopularPresenter;

import java.util.ArrayList;
import java.util.List;

public class PopularFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private PopularAdapter popularAdapter;
    private List<Popular> populars=new ArrayList<>();
    private EditText editText;
    private ImageView imageView;
    private String language="";
    private SwipeRefreshLayout swipeRefreshLayout;
    private int flag=0;//判断是否已经有语言选择
    private Toolbar toolbar;
    private String url="https://api.github.com/search/repositories?q=stars:>1&sort=stars";
    private PopularPresenter popularPresenter;
    public PopularFragment() {
    }

    public static PopularFragment newInstance() {
        PopularFragment fragment = new PopularFragment();
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
      View view=themeinflater.inflate(R.layout.fragment_popular, container, false);
      recyclerView=(RecyclerView)view.findViewById(R.id.show_popular);
      toolbar=(Toolbar)view.findViewById(R.id.popular_toolbar);
      toolbar.setTitle("Popular");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
      swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.swipe_popular);
      editText=(EditText)view.findViewById(R.id.search);
      imageView=(ImageView)view.findViewById(R.id.search_image);
      Context context=getActivity();
      swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_red_light, android.R.color.holo_green_light, android.R.color.holo_blue_bright, android.R.color.holo_orange_light);
      popularPresenter=new PopularPresenter(context,this);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        popularPresenter.getPopular(url);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },1000);
            }
        });
      imageView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              String editext=editText.getText().toString();
              String suburl1=url.substring(0,url.indexOf("q=")+2);
              String suburl2=url.substring(url.indexOf("&sort"),url.length());
              url=suburl1+editext+language+suburl2;
              popularPresenter.getPopular(url);
          }
      });//搜索
      layoutManager=new StaggeredGridLayoutManager(1, android.support.v7.widget.StaggeredGridLayoutManager.VERTICAL);
      recyclerView.setLayoutManager(layoutManager);
      popularAdapter=new PopularAdapter(context,recyclerView,populars);
      recyclerView.setAdapter(popularAdapter);
      popularAdapter.notifyDataSetChanged();
      popularPresenter.getPopular("https://api.github.com/search/repositories?q=stars:>1&sort=stars");
      return view;
    }
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    public void showToast()
    {
        Toast.makeText(getActivity(),"there is no result",Toast.LENGTH_LONG).show();
    }
    public void notifyChanged(List list)
    {
        popularAdapter.popularListChanged(list);
        popularAdapter.notifyDataSetChanged();
    }
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.trend_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    public void changeurl()
    {
        if (flag==0)
        {
            String suburl1=url.substring(0,url.indexOf("&sort"));
            String suburl2=url.substring(url.indexOf("&sort"),url.length());
            url=suburl1+language+suburl2;
            flag=1;
        }
        else {
            String suburl1=url.substring(0,url.indexOf("+language="));
            String suburl2=url.substring(url.indexOf("&sort"),url.length());
            url=suburl1+language+suburl2;
        }
        popularPresenter.getPopular(url);
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.cjiajia:
                language="+language=c++";
                changeurl();
                return true;
            case R.id.java:
                language="+language=java";
                changeurl();
                return true;
            case R.id.css:
                language="+language=css";
                changeurl();
                return true;
            case R.id.javascript:
                language="+language=javascript";
                changeurl();
                return true;
            case R.id.c:
                language="+language=c#";
                changeurl();
                return true;
            case R.id.all:
                language="";
                changeurl();
                flag=0;
                return true;
            default:
            return super.onOptionsItemSelected(item);
        }

    }
}
