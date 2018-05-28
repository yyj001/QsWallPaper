package com.ish.qswallpaper.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.ish.qswallpaper.R;
import com.ish.qswallpaper.view.HorizontalScrollImageView;

/**
 * @author ish
 * @date 2018/5/5.
 */

public class WallPaper extends BaseObservable {
    private String imageUrl;
    private Bitmap placeholder;
    private int like;

    public WallPaper(String imageUrl, Bitmap placeholder, int like) {
        this.imageUrl = imageUrl;
        this.placeholder = placeholder;
        this.like = like;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Bitmap getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(Bitmap placeholder) {
        this.placeholder = placeholder;
    }

    @Bindable
    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(final ImageView view, final String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .dontTransform()
                .error(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(view);
    }
}
