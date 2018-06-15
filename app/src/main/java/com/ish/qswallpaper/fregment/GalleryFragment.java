package com.ish.qswallpaper.fregment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bumptech.glide.Glide;
import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.ish.qswallpaper.R;
import com.ish.qswallpaper.adapter.GalleryItemAdapter;
import com.ish.qswallpaper.adapter.InnerAdapter;
import com.ish.qswallpaper.adapter.NewestAdapter;
import com.ish.qswallpaper.bean.WallPaper;
import com.ish.qswallpaper.databinding.FragmentGalleryBinding;
import com.ish.qswallpaper.manager.HeaderSpanSizeLookup;
import com.ish.qswallpaper.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ish
 * @date 2018/5/6.
 */

public class GalleryFragment extends Fragment implements NavigationTabStrip.OnTabStripSelectedIndexListener{

    private FragmentGalleryBinding mBinding;
    private List<WallPaper> mList;
    private List<WallPaper> mBannerList;
    private RecyclerView header;
    private LinearLayoutManager mOutLayoutManager;
    private LinearLayoutManager mInnerLayoutManager;
    private NewestAdapter mAdapter;
    private boolean ifGrid;

    /**
     * 加载网络图片的数据
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mList = new ArrayList<WallPaper>();
        mBannerList = new ArrayList<WallPaper>();
        for (int i = 1; i <= 23; ++i) {
            WallPaper wallPaper = new WallPaper("http://www.isssh.cn/qs/wallpaper_mini512/" + (i%23+1) +".jpg",
                    null,1);
            mList.add(wallPaper);
        }

        for (int i = 1; i <= 3; ++i) {
            WallPaper wallPaper = new WallPaper("http://www.isssh.cn/qs/banner/banner" + i +".jpg",
                    null,1);
            mBannerList.add(wallPaper);
        }
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_gallery,
                container,false);
        initView(inflater);
        return mBinding.getRoot();
    }

    public void initView(LayoutInflater inflater) {
        super.onStart();
        ifGrid = false;
        //设置layoutManager
        mOutLayoutManager = new LinearLayoutManager(getActivity());
        mInnerLayoutManager = new LinearLayoutManager(getActivity());
        mInnerLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mOutLayoutManager.setAutoMeasureEnabled(true);
        mInnerLayoutManager.setAutoMeasureEnabled(true);

        //为recyclerView设置lm
        mBinding.galleryRecyclerview.setLayoutManager(mOutLayoutManager);
        mBinding.galleryRecyclerview.setHasFixedSize(true);

        mAdapter = new NewestAdapter(mList, ScreenUtils.getWidth(mBinding));
        mBinding.setAdapter(mAdapter);

        //banner
        mBinding.headerRecyclerview.setLayoutManager(mInnerLayoutManager);
        mBinding.headerRecyclerview.setAdapter(new InnerAdapter(mBannerList));
        //初始化tabView
        mBinding.galleryTabbar.setTitles("  ", "  ");
        mBinding.galleryTabbar.setTabIndex(0, true);
        mBinding.galleryTabbar.setOnTabStripSelectedIndexListener(this);
    }

    @Override
    public void onStartTabSelected(String title, int index) {

    }

    /**
     * tabbar切换动画完成后，修改recyclerview的布局
     * @param title
     * @param index
     */
    @Override
    public void onEndTabSelected(String title, int index) {
        if(index==0){
            if(!ifGrid){return;}
            mBinding.galleryRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
            mBinding.setAdapter(mAdapter);
            //更换按钮图片
            Glide.with(getActivity())
                    .load(R.drawable.ic_line_b)
                    .into(mBinding.galleryLineBtn);
            Glide.with(getActivity()).load(R.drawable.ic_grid_g).into(mBinding.galleryNineBtn);
            ifGrid = false;
        }else{
            if(ifGrid){return;}
            GridLayoutManager glm = new GridLayoutManager(getActivity(), 3);
            mBinding.galleryRecyclerview.setLayoutManager(glm);
            mBinding.setAdapter(mAdapter);
            //更换按钮图片
            Glide.with(getActivity()).load(R.drawable.ic_line_g).into(mBinding.galleryLineBtn);
            Glide.with(getActivity()).load(R.drawable.ic_grid_b).into(mBinding.galleryNineBtn);
            ifGrid = true;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("abcc", "onDestroyView: ");
    }
}
