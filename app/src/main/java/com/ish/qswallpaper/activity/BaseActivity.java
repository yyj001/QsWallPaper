package com.ish.qswallpaper.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gyf.barlibrary.ImmersionBar;
import com.ish.qswallpaper.R;

/**
 * @author ish
 * @date 2018/5/14.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this)
                //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前
                // 设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .statusBarDarkFont(true, 0.2f)
                .barColor(R.color.white)
                .fitsSystemWindows(true)
                .init();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //必须调用该方法，防止内存泄漏
        ImmersionBar.with(this).destroy();
    }
}
