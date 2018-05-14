package com.ish.qswallpaper.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.ish.qswallpaper.R;
import com.ish.qswallpaper.bean.WallPaper;

import java.util.List;

/**
 * @author ish
 * @date 2018/5/6.
 */

public class GalleryItemAdapter extends RecyclerView.Adapter<GalleryItemAdapter.ViewHolder> {

    private List<WallPaper> list;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_toolBar = 1;
    private static View headerView;

    public GalleryItemAdapter(List<WallPaper> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //如果头部头部存在
        if(viewType == TYPE_HEADER && headerView!=null){
            return new ViewHolder(headerView);
        }
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_wallpaper, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(getItemViewType(position) == TYPE_HEADER){
            return;
        }
        int pos = getRealPosition(holder);
        WallPaper wallPaper = list.get(pos);
        String url = wallPaper.getImageUrl();
        Glide.with(holder.imageView.getContext())
                .load(url)
                .dontTransform()
                .error(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(holder.imageView);
    }


    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return headerView == null ? position : position - 1;
    }

    /**
     * 重写获取位置的类型
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if(headerView == null) {
            return TYPE_ITEM;
        }
        if(position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    /**
     * 插入头部
     * @param headerView
     */
    public void setHeaderView(View headerView) {
        GalleryItemAdapter.headerView = headerView;
        notifyItemInserted(0);
    }


    @Override
    public int getItemCount() {
        return headerView == null ? list.size() : list.size() + 1;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(View view) {
            super(view);
            if (view == headerView){
                return;
            }
            imageView = (ImageView) view.findViewById(R.id.gallery_item_imageview);
        }
    }
}
