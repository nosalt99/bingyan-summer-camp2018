package com.summer.bingyan.gitpopular.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.summer.bingyan.gitpopular.R;
import com.summer.bingyan.gitpopular.adapters.TrendingAdapter;
import com.summer.bingyan.gitpopular.data.Trend;
import com.summer.bingyan.gitpopular.presenter.TrendingPresenter;

import java.util.List;

public class TrendingFragment extends Fragment {
    private TrendingAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private final TrendingPresenter trendingPresenter=new TrendingPresenter(this);
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
        View view=inflater.inflate(R.layout.fragment_trending, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.show_trending);
        layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        trendingPresenter.getTrending("https://github.com/trending");
        return view;
    }
    public void setAdapter(List<Trend> list){
        Context context=getActivity();
        adapter = new TrendingAdapter(context,recyclerView, list);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
