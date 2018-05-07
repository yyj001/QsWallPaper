package com.ish.qswallpaper.fregment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ish.qswallpaper.R;
import com.ish.qswallpaper.databinding.FragmentGalleryBinding;
import com.ish.qswallpaper.databinding.FragmentInformationBinding;

/**
 * @author ish
 * @date 2018/5/6.
 */

public class InformationFragment extends Fragment {

    private FragmentInformationBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_information,
                container,false);
        return mBinding.getRoot();
    }
}

