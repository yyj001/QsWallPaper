package com.ish.qswallpaper.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ish.qswallpaper.fregment.CollectionFragment;
import com.ish.qswallpaper.fregment.GalleryFragment;
import com.ish.qswallpaper.fregment.InformationFragment;
import com.ish.qswallpaper.fregment.NewestFragment;

/**
 * @author ish
 * @date 2018/5/6.
 */

public class TabPageIndicatorAdapter extends FragmentPagerAdapter {
    public TabPageIndicatorAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new GalleryFragment();
                break;
            case 1:
                fragment = new NewestFragment();
                break;
            case 2:
                fragment = new CollectionFragment();
                break;
            case 3:
                fragment = new InformationFragment();
                break;
            default:
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
