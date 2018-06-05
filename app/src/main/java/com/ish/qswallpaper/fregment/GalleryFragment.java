package com.ish.qswallpaper.fregment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

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
    private List<WallPaper> list;
    private List<WallPaper> bannerList;
    private RecyclerView header;
    private LinearLayoutManager outLayoutManager;
    private LinearLayoutManager innerLayoutManager;
    private NewestAdapter adapter;
    private boolean ifGrid;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_gallery,
                container,false);
        //防止oncreateView多次导致内容增多
        list = new ArrayList<WallPaper>();
        bannerList = new ArrayList<WallPaper>();
        initView(inflater);
        return mBinding.getRoot();
    }

    public void initView(LayoutInflater inflater) {
        super.onStart();
        ifGrid = false;
        for (int i = 1; i <= 23; ++i) {
            WallPaper wallPaper = new WallPaper("http://www.isssh.cn/qs/wallpaper_mini512/" + (i%23+1) +".jpg",
                    null,1);
            list.add(wallPaper);
        }

        for (int i = 1; i <= 3; ++i) {
            WallPaper wallPaper = new WallPaper("http://www.isssh.cn/qs/banner/banner" + i +".jpg",
                    null,1);
            bannerList.add(wallPaper);
        }
        //设置layoutManager
        outLayoutManager = new LinearLayoutManager(getActivity());
        innerLayoutManager = new LinearLayoutManager(getActivity());
        innerLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        outLayoutManager.setAutoMeasureEnabled(true);
        innerLayoutManager.setAutoMeasureEnabled(true);

        //为recyclerView设置lm
        mBinding.galleryRecyclerview.setLayoutManager(outLayoutManager);
        mBinding.galleryRecyclerview.setHasFixedSize(true);

        adapter = new NewestAdapter(list, ScreenUtils.getWidth(mBinding));
        mBinding.setAdapter(adapter);

        //banner
        mBinding.headerRecyclerview.setLayoutManager(innerLayoutManager);
        mBinding.headerRecyclerview.setAdapter(new InnerAdapter(bannerList));
        //初始化tabView
        mBinding.galleryTabbar.setTitles("  ", "  ");
        mBinding.galleryTabbar.setTabIndex(0, true);
        mBinding.galleryTabbar.setOnTabStripSelectedIndexListener(this);
    }


    public void bigList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setAutoMeasureEnabled(true);
        mBinding.galleryRecyclerview.setLayoutManager(linearLayoutManager);
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
            mBinding.setAdapter(adapter);
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
            mBinding.setAdapter(adapter);
            //更换按钮图片
            Glide.with(getActivity()).load(R.drawable.ic_line_g).into(mBinding.galleryLineBtn);
            Glide.with(getActivity()).load(R.drawable.ic_grid_b).into(mBinding.galleryNineBtn);
            ifGrid = true;
        }
    }
}
