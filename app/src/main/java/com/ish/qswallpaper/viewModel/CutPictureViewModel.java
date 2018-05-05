package com.ish.qswallpaper.viewModel;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.ish.qswallpaper.R;
import com.ish.qswallpaper.databinding.ActivityCutPictureBinding;
import com.ish.qswallpaper.view.HorizontalScrollImageView;

/**
 * @author ish
 * @date 2018/5/5.
 */

public class CutPictureViewModel {

    private ActivityCutPictureBinding mBinding;
    private String imageUrl;
    private Drawable placeholder;

    public CutPictureViewModel(ActivityCutPictureBinding mBinding) {
        this.mBinding = mBinding;
    }

    public Drawable getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(Drawable placeholder) {
        this.placeholder = placeholder;
    }

    public void setImageUrl(String url) {
        this.imageUrl = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 在xml里面设置imageUrl属性后就可以自动调用这个方法
     * 第一个参数是该view，后面是imageUrl对应的值
     * @param view
     * @param imageUrl
     */
    @BindingAdapter({"bind:imageUrl","bind:placeholder"})
    public static void loadImage(final HorizontalScrollImageView view, String imageUrl,
                                 Drawable placeholder) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .asBitmap()
                .dontTransform()
                .placeholder(placeholder)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .listener(new RequestListener<String, Bitmap>() {
                              @Override
                              public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                                  return false;
                              }

                              @Override
                              public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target,
                                                             boolean isFromMemoryCache, boolean isFirstResource) {
                                  view.setPicSize(resource.getWidth(), resource.getHeight());
                                  return false;
                              }
                          }
                )
                .into(view);
    }
}
