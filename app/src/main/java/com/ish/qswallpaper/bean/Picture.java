package com.ish.qswallpaper.bean;

import android.graphics.Bitmap;

/**
 *@author ish
 *@date 2018/5/5.
 */

public class Picture {
    private String url;
    private Bitmap placeholder;

    public Picture(String url,Bitmap placeholder){
        this.url = url;
        this.placeholder = placeholder;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Bitmap getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(Bitmap placeholder) {
        this.placeholder = placeholder;
    }
}
