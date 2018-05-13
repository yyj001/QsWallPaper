package com.ish.qswallpaper.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ish.qswallpaper.R;
import com.ish.qswallpaper.bean.WallPaper;

import java.util.List;

/**
 * @author ish
 * @date 2018/5/7.
 */

public class InnerAdapter extends RecyclerView.Adapter<InnerAdapter.ViewHolder> {

        private List<WallPaper> list;

        public InnerAdapter(List<WallPaper> list) {
            this.list = list;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_inner_wallpaper, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }


    @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if(position == getItemCount()-1){
                holder.layout.setPadding(0,0,50,0);
            }
            WallPaper wallPaper = list.get(position);
            String url = wallPaper.getImageUrl();
            Glide.with(holder.imageView.getContext())
                    .load(url)
                    .dontTransform()
                    .error(R.drawable.ic_launcher_background)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .crossFade()
                    .into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            LinearLayout layout;
            public ViewHolder(View view) {
                super(view);
                imageView = (ImageView) view.findViewById(R.id.inner_item_imageview);
                layout = (LinearLayout) view.findViewById(R.id.inner_layout);
            }
        }
    }

