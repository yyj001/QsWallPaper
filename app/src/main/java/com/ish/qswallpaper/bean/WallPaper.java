package com.ish.qswallpaper.bean;

import android.graphics.Bitmap;

/**
 * @author ish
 * @date 2018/5/5.
 */

public class WallPaper {
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

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
