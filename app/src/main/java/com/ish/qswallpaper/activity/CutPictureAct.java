package com.ish.qswallpaper.activity;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.ish.qswallpaper.R;
import com.ish.qswallpaper.databinding.ActivityCutPictureBinding;
import com.ish.qswallpaper.view.HorizontalScrollImageView;
import com.ish.qswallpaper.viewModel.CutPictureViewModel;

/**
 * @author ish
 */
public class CutPictureAct extends AppCompatActivity {

    private ActivityCutPictureBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        //隐藏状态栏
        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_cut_picture);
        String url = "http://www.isssh.cn/test/p1.jpg";
        CutPictureViewModel viewModel = new CutPictureViewModel(mBinding);
        viewModel.setImageUrl(url);
        viewModel.setPlaceholder(getResources().getDrawable(R.drawable.ic_launcher_background));
        mBinding.setViewModel(viewModel);
    }
}