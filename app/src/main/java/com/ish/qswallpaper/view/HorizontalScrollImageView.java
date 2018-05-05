package com.ish.qswallpaper.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;

import java.lang.reflect.Field;

/**
 * @author ish
 * @date 2018/5/4
 */

@SuppressLint("AppCompatCustomView")
public class HorizontalScrollImageView extends ImageView {

    private String TAG = "aaa";
    //点击的位置
    private float mx;
    //图片实际高度
    private float picHeight;
    //图片实际宽度
    private float picWidth;
    //图片展示高度（屏幕高度）
    private int usePicHeight;
    //图片展示宽度
    private int usePicWidth;
    //屏幕高度
    private int screenHeight;
    //屏幕宽度
    private int screenWidth;
    //当前x
    private int curX;

    private int moveSum = 0;

    public HorizontalScrollImageView(Context context) {
        super(context);
        initData();
    }

    public HorizontalScrollImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    private void initData() {
        this.screenWidth = getWidth();
        this.screenHeight = getHeight();
    }

    /**
     * 获取图片资源的大小,在设置了initData后使用
     *
     * @param realWidth
     * @param realHeight
     */
    public void setPicSize(int realWidth, int realHeight) {
        initData();
        this.picWidth = realWidth;
        this.picHeight = realHeight;
        this.usePicHeight = screenHeight;
        this.usePicWidth = (int)(picWidth / picHeight * screenHeight);
    }

    /**
     * 移动图片
     *
     * @param event
     * @return
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                mx = event.getX();
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                curX = (int)event.getX();
                float move = mx - curX;
                //如果最后一次会移出边界,则修改move的值使其刚好移到边界
                if (Math.abs(moveSum + move) > ((usePicWidth - screenWidth) / 2)) {
                    if (moveSum > 0) {
                        move = (usePicWidth - screenWidth) / 2 - moveSum;
                    } else {
                        move = -(usePicWidth - screenWidth) / 2 - moveSum;
                    }
                }
                moveSum += move;
                scrollBy((int) move, 0);
                mx = curX;
                break;
            }
            case MotionEvent.ACTION_UP: {
                curX = (int)event.getX();
            }
            default:
        }
        return true;
    }


}
