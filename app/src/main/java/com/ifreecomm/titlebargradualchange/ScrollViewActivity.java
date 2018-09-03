package com.ifreecomm.titlebargradualchange;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ifreecomm.titlebargradualchange.utils.StatusbarUtils;
import com.ifreecomm.titlebargradualchange.widget.ObservableScrollView;

public class ScrollViewActivity extends AppCompatActivity implements ObservableScrollView.OnObservableScrollViewListener {

    private static final String TAG = "MainActivity";
    private ObservableScrollView mScrollView;
    private ImageView tvMainTopContent;
    private LinearLayout mHeaderContent;
    private int mHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置透明状态栏
        StatusbarUtils.enableTranslucentStatusbar(this);
        setContentView(R.layout.activity_scroll_view);
        initView();
    }

    @SuppressLint("NewApi")
    private void initView() {

        mScrollView = (ObservableScrollView) findViewById(R.id.sv_main_content);
        tvMainTopContent = findViewById(R.id.tv_main_topContent);
        mHeaderContent = (LinearLayout) findViewById(R.id.ll_header_content);

        // 测量头部的高度

        tvMainTopContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                tvMainTopContent.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //这里取的高度应该为图片的高度-标题栏
                int tvMainTopContentHeight = tvMainTopContent.getHeight();
                int mHeaderContentHeight = mHeaderContent.getHeight();
                Log.e(TAG, "tvMainTopContentHeight: " + tvMainTopContentHeight + " mHeaderContentHeight: " + mHeaderContentHeight);
                mHeight = tvMainTopContentHeight - mHeaderContentHeight;
                Log.e(TAG, "mHeight :" + mHeight);

            }
        });
        //注册滑动监听
        mScrollView.setOnObservableScrollViewListener(ScrollViewActivity.this);

        findViewById(R.id.iv_header_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onObservableScrollViewListener(int l, int t, int oldl, int oldt) {

        if (t > mHeight) {
            t = mHeight;
        }
        if (t < 0) {
            t = 0;
        }

        float persent = t * 1.0f / mHeight;

        float alpha = (255 * persent);//得到透明度
        mHeaderContent.setBackgroundColor(Color.argb((int) alpha, 48, 63, 159));

    }
}
