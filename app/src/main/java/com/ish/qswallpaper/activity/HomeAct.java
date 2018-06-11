package com.ish.qswallpaper.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import com.ish.qswallpaper.R;

import com.ish.qswallpaper.adapter.TabPageIndicatorAdapter;

import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;

public class HomeAct extends BaseActivity {


    private ViewPager viewPager;
    final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
    NavigationTabBar navigationTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb);

        FragmentPagerAdapter fragmentPagerAdapter = new TabPageIndicatorAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(fragmentPagerAdapter);
        initData();
    }

    private void initData(){
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_home),
                        Color.parseColor("#ffffff")
                ).title("首页")
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_home_select))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_discover),
                        Color.parseColor("#ffffff")
                ).title("最新")
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_discover_fill))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_like),
                        Color.parseColor("#ffffff")
                ).title("收藏")
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_like_fill))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_more),
                        Color.parseColor("#ffffff")
                ).title("更多")
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_more_select))
                        .build()
        );
        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 0);
    }

}

