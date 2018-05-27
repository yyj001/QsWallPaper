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

import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.ish.qswallpaper.R;
import com.ish.qswallpaper.adapter.NewestAdapter;
import com.ish.qswallpaper.bean.WallPaper;
import com.ish.qswallpaper.databinding.FragmentNewestBinding;
import com.ish.qswallpaper.databinding.HeaderNewestBinding;
import com.ish.qswallpaper.manager.HeaderSpanSizeLookup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ish
 * @date 2018/5/25.
 */

public class NewestFragment extends Fragment implements NavigationTabStrip.OnTabStripSelectedIndexListener
        , View.OnClickListener {
    private FragmentNewestBinding mBinding;
    private List<WallPaper> list;
    private HeaderNewestBinding headerBinding;
    private NewestAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_newest,
                container, false);

        initData(inflater);
        return mBinding.getRoot();
    }

    public void initData(LayoutInflater inflater) {
        list = new ArrayList<WallPaper>();
        for (int i = 1; i <= 23; ++i) {
            WallPaper wallPaper = new WallPaper("http://www.isssh.cn/qs/wallpaper_mini512/" + (i % 23 + 1) + ".jpg",
                    null, 1);
            list.add(wallPaper);
        }
        mBinding.newestRecylerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //头部binding
        headerBinding = DataBindingUtil.inflate(inflater, R.layout.header_newest,
                mBinding.newestRecylerView, false);
        headerBinding.newestTabbar.setTitles("平铺", "分列");
        headerBinding.newestTabbar.setTabIndex(0, true);
        headerBinding.newestTabbar.setOnTabStripSelectedIndexListener(this);
        headerBinding.newsLineBtn.setOnClickListener(this);
        headerBinding.newsNineBtn.setOnClickListener(this);

        adapter = new NewestAdapter(list);
        adapter.setHeaderView(headerBinding);
        mBinding.setAdapter(adapter);

    }

    @Override
    public void onStartTabSelected(String title, int index) {
    }

    @Override
    public void onEndTabSelected(String title, int index) {
        Toast.makeText(getActivity(), "2", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {
        Resources resources = getActivity().getResources();
        switch (v.getId()) {
            case R.id.news_line_btn:
                mBinding.newestRecylerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                adapter.setIfStragger(false);
                mBinding.newestRecylerView.setAdapter(adapter);

                headerBinding.newestTabbar.setTabIndex(0,true);
                headerBinding.newsLineBtn.setImageDrawable(resources.getDrawable(R.drawable.ic_line_b));
                headerBinding.newsNineBtn.setImageDrawable(resources.getDrawable(R.drawable.ic_grid_g));
                break;
            case R.id.news_nine_btn:
                //设置瀑布流
                StaggeredGridLayoutManager sglm = new StaggeredGridLayoutManager(2
                        , StaggeredGridLayoutManager.VERTICAL);
                mBinding.newestRecylerView.setLayoutManager(sglm);
                adapter.setIfStragger(true);
                mBinding.newestRecylerView.setAdapter(adapter);
                headerBinding.newestTabbar.setTabIndex(1,true);
                headerBinding.newsLineBtn.setImageDrawable(resources.getDrawable(R.drawable.ic_line_g));
                headerBinding.newsNineBtn.setImageDrawable(resources.getDrawable(R.drawable.ic_grid_b));
                break;
            default:
        }
    }
}
