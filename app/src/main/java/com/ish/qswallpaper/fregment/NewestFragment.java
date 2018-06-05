package com.ish.qswallpaper.fregment;

import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.ish.qswallpaper.R;
import com.ish.qswallpaper.adapter.NewestAdapter;
import com.ish.qswallpaper.bean.WallPaper;
import com.ish.qswallpaper.databinding.FragmentNewestBinding;
import com.ish.qswallpaper.databinding.HeaderNewestBinding;
import com.ish.qswallpaper.manager.HeaderSpanSizeLookup;
import com.ish.qswallpaper.manager.WrapHeightGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ish
 * @date 2018/5/25.
 */

public class NewestFragment extends Fragment implements NavigationTabStrip.OnTabStripSelectedIndexListener {
    private FragmentNewestBinding mBinding;
    private List<WallPaper> list;
    private HeaderNewestBinding headerBinding;
    private NewestAdapter adapter;
    private boolean ifGrid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_newest,
                container, false);

        //initData(inflater);
        return mBinding.getRoot();
    }

    public void initData(LayoutInflater inflater) {
        //每次都要设置
        ifGrid = false;
        list = new ArrayList<WallPaper>();
        for (int i = 1; i <= 25; ++i) {
            WallPaper wallPaper = new WallPaper("http://www.isssh.cn/qs/wallpaper_mini512/" + (i % 23 + 1) + ".jpg",
                    null, 1);
            list.add(wallPaper);
        }
        mBinding.newestRecylerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //头部binding
        headerBinding = DataBindingUtil.inflate(inflater, R.layout.header_newest,
                mBinding.newestRecylerView, false);
        headerBinding.newestTabbar.setTitles("  ", "  ");
        headerBinding.newestTabbar.setTabIndex(0, true);
        headerBinding.newestTabbar.setOnTabStripSelectedIndexListener(this);

        //adapter = new NewestAdapter(list);
        adapter.setHeaderView(headerBinding);
        mBinding.setAdapter(adapter);
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
            mBinding.newestRecylerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mBinding.newestRecylerView.setAdapter(adapter);
            //更换按钮图片
            Glide.with(getActivity())
                    .load(R.drawable.ic_line_b)
                    .into(headerBinding.newsLineBtn);
            Glide.with(getActivity()).load(R.drawable.ic_grid_g).into(headerBinding.newsNineBtn);
            ifGrid = false;
        }else{
            if(ifGrid){return;}
            GridLayoutManager glm = new GridLayoutManager(getActivity(), 3);
            glm.setSpanSizeLookup(new HeaderSpanSizeLookup(glm));
            mBinding.newestRecylerView.setLayoutManager(glm);
            mBinding.newestRecylerView.setAdapter(adapter);
            //更换按钮图片
            Glide.with(getActivity()).load(R.drawable.ic_line_g).into(headerBinding.newsLineBtn);
            Glide.with(getActivity()).load(R.drawable.ic_grid_b).into(headerBinding.newsNineBtn);
            ifGrid = true;
        }
    }
}
