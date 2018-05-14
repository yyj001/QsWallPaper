package com.ish.qswallpaper.activity;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.ish.qswallpaper.R;
import com.ish.qswallpaper.databinding.ActivityCutPictureBinding;
import com.ish.qswallpaper.internet.ProgressInterceptor;
import com.ish.qswallpaper.listener.ProgressListener;
import com.ish.qswallpaper.viewModel.CutPictureViewModel;

/**
 * @author ish
 */
public class CutPictureAct extends AppCompatActivity {

    private ActivityCutPictureBinding mBinding;
    private ProgressDialog progressDialog;
    private CutPictureViewModel viewModel;
    CutPictureViewModel.MyProgressListener progressListener = new CutPictureViewModel.MyProgressListener() {
        @Override
        public void showProgress() {
            progressDialog.show();
        }

        @Override
        public void dismissProgress() {
            progressDialog.dismiss();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStateBar();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_cut_picture);
        initData();
    }

    /**
     * 隐藏状态栏
     */
    private void hideStateBar() {
        Window window = getWindow();
        //隐藏状态栏
        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
    }

    private void initData() {
        String url = "http://www.isssh.cn/test/p1.jpg";
        //设置ViewMode
        viewModel = new CutPictureViewModel(mBinding, progressListener);
        viewModel.setImageUrl(url);
        viewModel.setPlaceholder(getResources().getDrawable(R.drawable.placeholder));
        mBinding.setViewModel(viewModel);
        //progressDialog初始化
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMessage("加载中");
        //progressDialog设置监听器
        ProgressInterceptor.addListener(url, new ProgressListener() {
            @Override
            public void onProgress(int progress) {
                progressDialog.setProgress(progress);
            }
        });
    }
}