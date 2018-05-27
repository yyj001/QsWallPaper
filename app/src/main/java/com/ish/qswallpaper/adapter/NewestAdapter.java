package com.ish.qswallpaper.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ish.qswallpaper.R;
import com.ish.qswallpaper.bean.WallPaper;
import com.ish.qswallpaper.databinding.FragmentNewestBinding;
import com.ish.qswallpaper.databinding.HeaderNewestBinding;
import com.ish.qswallpaper.databinding.ItemWallpaperBinding;

import java.util.List;

/**
 * @author ish
 * @date 2018/5/25.
 */

public class NewestAdapter extends RecyclerView.Adapter<NewestAdapter.ViewHolder> {

    private List<WallPaper> list;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static View headerView;

    public NewestAdapter(List<WallPaper> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //如果头部头部存在
        if (viewType == TYPE_HEADER && headerView != null) {
            HeaderNewestBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
            R.layout.header_newest, parent, false);
            return new ViewHolder(viewDataBinding);
        }
        ItemWallpaperBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_wallpaper, parent, false);
        return new ViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) {
            return;
        }
        int pos = getRealPosition(holder);
        ((ItemWallpaperBinding)holder.binding).setImage(list.get(pos));

    }


    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return headerView == null ? position : position - 1;
    }

    /**
     * 重写获取位置的类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (headerView == null) {
            return TYPE_ITEM;
        }
        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    /**
     * 插入头部
     *
     * @param headerView
     */
    public void setHeaderView(View headerView) {
        NewestAdapter.headerView = headerView;
        notifyItemInserted(0);
    }


    @Override
    public int getItemCount() {
        return headerView == null ? list.size() : list.size() + 1;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            if (binding.getRoot() == headerView) {
                return;
            }
            this.binding = binding;
        }

        public ViewDataBinding getBinding(){
            return this.binding;
        }
    }
}
