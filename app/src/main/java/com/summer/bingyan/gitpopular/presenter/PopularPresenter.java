package com.summer.bingyan.gitpopular.presenter;

import android.content.Context;

import com.summer.bingyan.gitpopular.data.Popular;
import com.summer.bingyan.gitpopular.fragments.PopularFragment;
import com.summer.bingyan.gitpopular.model.PopularModel;

import java.util.ArrayList;
import java.util.List;

public class PopularPresenter {
    private PopularFragment popularFragment;
    private PopularModel popularModel;
    private Context context;
    private List<Popular>populars=new ArrayList<>();
    public PopularPresenter(Context context,PopularFragment popularFragment)
    {
        this.popularFragment=popularFragment;
        this.context=context;
    }
    public void getPopular(String url)
    {
        popularModel=new PopularModel(context,new PopularModel.PopularCallback() {
            @Override
            public void onSuccess(List list) {
                populars.clear();
                populars.addAll(list);
                popularFragment.notifyChanged(list);
                if (list.size()==0)
                {
                    popularFragment.showToast();
                }
            }
            public void onFail()
            {
                popularFragment.showToast();
            }
        });
        popularModel.getPopular(url);
    }
}
