package com.ish.qswallpaper.viewModel;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.ish.qswallpaper.databinding.ActivityCutPictureBinding;
import com.ish.qswallpaper.view.HorizontalScrollImageView;

/**
 * @author ish
 * @date 2018/5/5.
 */

public class CutPictureViewModel {

    private ActivityCutPictureBinding mBinding;
    private static String imageUrl;
    private Drawable placeholder;
    static MyProgressListener progressListener;

    public CutPictureViewModel(ActivityCutPictureBinding mBinding,MyProgressListener listener) {
        this.mBinding = mBinding;
        progressListener = listener;
    }

    public Drawable getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(Drawable placeholder) {
        this.placeholder = placeholder;
    }

    public void setImageUrl(String url) {
        imageUrl = url;
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
    public static void
    loadImage(final HorizontalScrollImageView view, final String imageUrl,
                                 Drawable placeholder) {
        //开始下载显示ProgressDialog
        progressListener.showProgress();
        Glide.with(view.getContext())
                .load(imageUrl)
                .asBitmap()
                .dontTransform()
                .placeholder(placeholder)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .listener(new RequestListener<String, Bitmap>() {
                              @Override
                              public boolean onException(Exception e, String model, Target<Bitmap> target
                                      , boolean isFirstResource) {
                                  return false;
                              }

                              @Override
                              public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target,
                                                             boolean isFromMemoryCache, boolean isFirstResource) {
                                  view.setPicSize(resource.getWidth(), resource.getHeight());
                                  //下载完后隐藏progressDialog
                                  progressListener.dismissProgress();
                                  return false;
                              }
                          }
                )
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .into(view);
    }

    /**
     * 给Activity回调更新progress
     */
    public interface MyProgressListener{

        public void showProgress();

        public void dismissProgress();
    }
}
