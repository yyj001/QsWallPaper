package com.ish.qswallpaper.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;


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
    private static HeaderNewestBinding headerBinding;
    //屏幕宽度
    private int width;

    public NewestAdapter(List<WallPaper> list, int width) {
        this.list = list;
        this.width = width;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //如果头部头部存在
        if (viewType == TYPE_HEADER && headerBinding != null) {
            return new ViewHolder(headerBinding);
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
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
//        //网格布局,修改高度，隐藏点赞
        if (lp != null && lp instanceof GridLayoutManager.LayoutParams) {
            ViewGroup.LayoutParams params = ((ItemWallpaperBinding) holder.binding).newestItemImageview.getLayoutParams();
            params.height = width / 3;
            ((ItemWallpaperBinding) holder.binding).newestItemImageview.setLayoutParams(params);
            ((ItemWallpaperBinding) holder.binding).newestItemLikenumber.setVisibility(View.GONE);
            ((ItemWallpaperBinding) holder.binding).newestItemLikeview.setVisibility(View.GONE);
        }
        ((ItemWallpaperBinding) holder.binding).setImage(list.get(pos));
    }


    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return headerBinding == null ? position : position - 1;
    }

    /**
     * 重写获取位置的类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (headerBinding == null) {
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
     * @param binding
     */
    public void setHeaderView(HeaderNewestBinding binding) {
        NewestAdapter.headerBinding = binding;
        notifyItemInserted(0);
    }


    @Override
    public int getItemCount() {
        return headerBinding == null ? list.size() : list.size() + 1;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            if (binding == headerBinding) {
                return;
            }
            this.binding = binding;
        }

        public ViewDataBinding getBinding() {
            return this.binding;
        }
    }
}
