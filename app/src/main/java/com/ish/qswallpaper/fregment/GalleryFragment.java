package com.ish.qswallpaper.fregment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ish.qswallpaper.R;
import com.ish.qswallpaper.adapter.GalleryItemAdapter;
import com.ish.qswallpaper.bean.WallPaper;
import com.ish.qswallpaper.databinding.FragmentGalleryBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ish
 * @date 2018/5/6.
 */

public class GalleryFragment extends Fragment{

    private FragmentGalleryBinding mBinding;
    private List<WallPaper> list = new ArrayList<WallPaper>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_gallery,
                container,false);
        initView();
        return mBinding.getRoot();
    }

    public void initView() {
        super.onStart();
        for (int i = 1; i <= 500; ++i) {
            WallPaper wallPaper = new WallPaper("http://www.isssh.cn/qs/wallpaper_mini512/" + (i%23+1) +".jpg",
                    null,1);
            list.add(wallPaper);
        }
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        //final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setAutoMeasureEnabled(true);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mBinding.galleryRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                layoutManager.invalidateSpanAssignments(); //防止第一行到顶部有空白区域
            }
        });
        mBinding.galleryRecyclerview.setLayoutManager(layoutManager);
        mBinding.galleryRecyclerview.setHasFixedSize(true);
        GalleryItemAdapter adapter = new GalleryItemAdapter(list);
        mBinding.galleryRecyclerview.setAdapter(adapter);


        mBinding.getRoot().findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setAutoMeasureEnabled(true);
                mBinding.galleryRecyclerview.setLayoutManager(linearLayoutManager);

            }
        });
        mBinding.getRoot().findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.galleryRecyclerview.setLayoutManager(layoutManager);
            }
        });
    }
}
