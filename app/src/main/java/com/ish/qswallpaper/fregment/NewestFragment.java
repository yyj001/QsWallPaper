package com.ish.qswallpaper.fregment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ish.qswallpaper.R;
import com.ish.qswallpaper.adapter.NewestAdapter;
import com.ish.qswallpaper.bean.WallPaper;
import com.ish.qswallpaper.databinding.FragmentInformationBinding;
import com.ish.qswallpaper.databinding.FragmentNewestBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ish
 * @date 2018/5/25.
 */

public class NewestFragment extends Fragment {
    private FragmentNewestBinding mBinding;
    private List<WallPaper> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_newest,
                container,false);
        initData();
        return mBinding.getRoot();
    }

    public void initData(){
        list = new ArrayList<WallPaper>();
        for (int i = 1; i <= 23; ++i) {
            WallPaper wallPaper = new WallPaper("http://www.isssh.cn/qs/wallpaper_mini512/" + (i%23+1) +".jpg",
                    null,1);
            list.add(wallPaper);
        }
        mBinding.newestRecylerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置header
        View headerLayout = LayoutInflater.from(getActivity()).inflate(R.layout.header_gallery,
                mBinding.newestRecylerView, false);
        NewestAdapter adapter = new NewestAdapter(list);
        adapter.setHeaderView(headerLayout);
        mBinding.setAdapter(adapter);

    }
}
