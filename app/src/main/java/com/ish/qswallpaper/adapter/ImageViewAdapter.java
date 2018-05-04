package com.ish.qswallpaper.adapter;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

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

public class ImageViewAdapter {
    @BindingAdapter("android:src")
    public static void setSrc(HorizontalScrollImageView view, Bitmap bitmap) {
        view.setImageBitmap(bitmap);
    }

    @BindingAdapter("android:src")
    public static void setSrc(HorizontalScrollImageView view, int resId) {
        view.setImageResource(resId);
    }

    @BindingAdapter({"app:imageUrl", "app:placeHolder", "app:error"})
    public static void loadImage(final HorizontalScrollImageView imageView, String url,
                                 Drawable holderDrawable, Drawable errorDrawable) {
        Glide.with(imageView.getContext())
                .load(url)
                .asBitmap()
                .dontTransform()
                .placeholder(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .listener(new RequestListener<String, Bitmap>() {
                              @Override
                              public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                                  return false;
                              }

                              @Override
                              public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target,
                                                             boolean isFromMemoryCache, boolean isFirstResource) {
                                  imageView.setPicSize(resource.getWidth(),resource.getHeight());
                                  return false;
                              }
                          }
                )
                .into(imageView);
    }

}
