package com.ish.qswallpaper.utils;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * @author yangyijian
 * @date 2018/6/6
 * 屏幕相关的类
 */
public class ScreenUtils {
    /**
     * 输出屏幕宽度
     * @param binding
     * @return
     */
    static public int getWidth(ViewDataBinding binding){
        WindowManager wm = (WindowManager) binding.getRoot().getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        // 屏幕宽度（像素）
        int width = dm.widthPixels;
        return width;
    }
}
