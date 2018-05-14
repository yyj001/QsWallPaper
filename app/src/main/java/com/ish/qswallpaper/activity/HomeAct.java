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

public class HomeAct extends BaseActivity implements ViewPager.OnPageChangeListener {


    //private BottomNavigationView navigation;
    private ViewPager viewPager;
    final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
    NavigationTabBar navigationTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_home);

//        navigation = (BottomNavigationView) findViewById(R.id.navigation_bar);
//        navigation.setOnNavigationItemSelectedListener(this);
        navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb);


        FragmentPagerAdapter fragmentPagerAdapter = new TabPageIndicatorAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.setOnPageChangeListener(this);
        initData();
    }

    private void initData(){
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_home),
                        Color.parseColor("#222222")
                ).title("首页")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_dashboard),
                        Color.parseColor("#222222")
                ).title("最新")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_like),
                        Color.parseColor("#222222")
                ).title("收藏")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_more),
                        Color.parseColor("#222222")
                ).title("更多")
                        .build()
        );
        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 0);


//        navigationTabBar.setTitleMode(NavigationTabBar.TitleMode.ACTIVE);
//        navigationTabBar.setBadgeGravity(NavigationTabBar.BadgeGravity.BOTTOM);
//        navigationTabBar.setBadgePosition(NavigationTabBar.BadgePosition.CENTER);
//        navigationTabBar.setIsBadged(true);
//        navigationTabBar.setIsTitled(true);
//        navigationTabBar.setIsTinted(true);
//        navigationTabBar.setIsBadgeUseTypeface(true);
//        navigationTabBar.setBadgeBgColor(Color.RED);
//        navigationTabBar.setBadgeTitleColor(Color.WHITE);
//        navigationTabBar.setIsSwiped(true);
//        navigationTabBar.setBgColor(Color.BLACK);
//        navigationTabBar.setBadgeSize(10);
//        navigationTabBar.setTitleSize(10);
//        navigationTabBar.setIconSizeFraction(0.5f);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                //navigation.setSelectedItemId(R.id.navigation_home);
                break;
            case 1:
                //navigation.setSelectedItemId(R.id.navigation_dashboard);
                break;
            case 2:
                //navigation.setSelectedItemId(R.id.navigation_notifications);
                break;
            default:
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.navigation_home:
//                viewPager.setCurrentItem(0);
//                return true;
//            case R.id.navigation_dashboard:
//                viewPager.setCurrentItem(1);
//                return true;
//            case R.id.navigation_notifications:
//                viewPager.setCurrentItem(2);
//                return true;
//            default:
//        }
//        return false;
//    }

}

