package com.summer.bingyan.gitpopular.activities;
//底部tab Fragments的加载以及tab的监听事件

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.summer.bingyan.gitpopular.R;
import com.summer.bingyan.gitpopular.adapters.PageAdapter;
import com.summer.bingyan.gitpopular.fragments.SettingFragment;
import com.summer.bingyan.gitpopular.utils.FragmentGnerator;

public class BottomTabActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private Fragment[] mFragments;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_tab);
        mFragments = FragmentGnerator.getFragements();
        initView();
    }

    public void initView() {
        tabLayout = (TabLayout) findViewById(R.id.bottom_tab_layout);
        viewPager=(ViewPager)findViewById(R.id.viewpage);
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),4));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setSelectedTabIndicatorColor(Color.rgb(0,0,255));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                for (int i=0;i<tabLayout.getTabCount();i++)
                {
                    View view = tabLayout.getTabAt(i).getCustomView();
                    TextView textView=view.findViewById(R.id.tab_text);
                    if (i==tab.getPosition())
                    {
                        textView.setTextColor(Color.rgb(130,206,250));
                    }
                    else
                    {
                        textView.setTextColor(Color.rgb(0,0,0));
                    }
                }

            }
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        for(int i=0;i<4;i++){
            tabLayout.addTab(tabLayout.newTab().setCustomView(FragmentGnerator.getTabView(this,i)));
        }

    }
}

