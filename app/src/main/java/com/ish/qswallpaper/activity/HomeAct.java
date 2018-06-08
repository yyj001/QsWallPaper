package com.ish.qswallpaper.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
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
                        Color.parseColor("#000000")
                ).title("首页")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_type),
                        Color.parseColor("#000000")
                ).title("最新")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_like),
                        Color.parseColor("#000000")
                ).title("收藏")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_more),
                        Color.parseColor("#000000")
                ).title("更多")
                        .build()
        );
        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 0);
    }

}

