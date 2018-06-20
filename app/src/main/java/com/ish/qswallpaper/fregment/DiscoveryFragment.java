package com.ish.qswallpaper.fregment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ish.qswallpaper.R;
import com.ish.qswallpaper.databinding.FragmentDiscoveryBinding;

/**
 *
 * @author ish
 * @date 2018/5/6.
 */
public class DiscoveryFragment extends Fragment {

    private FragmentDiscoveryBinding mBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_discovery,
                container,false);
        return mBinding.getRoot();
    }
}
