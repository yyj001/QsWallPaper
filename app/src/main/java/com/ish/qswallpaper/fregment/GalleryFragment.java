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
import android.widget.ImageView;

import com.ish.qswallpaper.R;
import com.ish.qswallpaper.adapter.GalleryItemAdapter;
import com.ish.qswallpaper.adapter.InnerAdapter;
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
    private List<WallPaper> bannerList = new ArrayList<WallPaper>();
    private RecyclerView header;
    private LinearLayoutManager outLayoutManager;
    private LinearLayoutManager innerLayoutManager;

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
        for (int i = 1; i <= 30; ++i) {
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
        GalleryItemAdapter adapter = new GalleryItemAdapter(list);

        View h = LayoutInflater.from(getActivity()).inflate(R.layout.header_gallery,
                mBinding.galleryRecyclerview, false);
        header = (RecyclerView) h.findViewById(R.id.inner_recyclerview);
        header.setLayoutManager(innerLayoutManager);
        header.setAdapter(new InnerAdapter(bannerList));
//        header = LayoutInflater.from(getActivity()).inflate(R.layout.header_gallery,
//                mBinding.galleryRecyclerview, false);
        adapter.setHeaderView(header);

        mBinding.galleryRecyclerview.setAdapter(adapter);
    }


    public void bigList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setAutoMeasureEnabled(true);
        mBinding.galleryRecyclerview.setLayoutManager(linearLayoutManager);
    }
}
