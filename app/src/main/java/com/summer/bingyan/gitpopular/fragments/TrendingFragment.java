package com.summer.bingyan.gitpopular.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.summer.bingyan.gitpopular.R;

public class TrendingFragment extends Fragment {

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trending, container, false);
    }
}
