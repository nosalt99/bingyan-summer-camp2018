package com.summer.bingyan.gitpopular.fragments;

import android.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.summer.bingyan.gitpopular.R;

public class SettingFragment extends Fragment implements View.OnClickListener
{
private TextView change_theme;
public static int theme_selected=1;
private Toolbar toolbar;
    public SettingFragment() {
    }

    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
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
        View view=themeinflater.inflate(R.layout.fragment_setting, container, false);
        change_theme=view.findViewById(R.id.change_theme);
        toolbar=(Toolbar)view.findViewById(R.id.setting_toolbar);
        toolbar.setTitle("Setting");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        change_theme.setOnClickListener(this);
        return view;
    }
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.change_theme:
                new AlertDialog.Builder(getActivity()).setTitle("选择主题")
                        .setSingleChoiceItems(new String[]{"red", "blue", "green", "purple", "white", "yellow"},
                                theme_selected, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                      theme_selected=which;
                                      dialog.dismiss();
                                    }}).setNegativeButton("取消",null).show();
        }

    }
}
