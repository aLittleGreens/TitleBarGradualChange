package com.ifreecomm.titlebargradualchange;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ifreecomm.titlebargradualchange.adapter.RecycleAdapter;
import com.ifreecomm.titlebargradualchange.utils.StatusbarUtils;

public class RecyclerActivity extends AppCompatActivity {
    private static final String TAG = "RecyclerActivity";
    String[] mData = {"a", "b", "d", "e", "f", "g", "h", "h", "a", "b", "d", "e", "f", "g", "h", "h", "a", "b", "d", "e", "f", "g", "h", "h", "a", "b", "d", "e", "f", "g", "h", "h"};
    private LinearLayout tvTitle;
    private LinearLayoutManager layoutManager;
    private int mHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置透明状态栏
        StatusbarUtils.enableTranslucentStatusbar(this);
        setContentView(R.layout.activity_recycler);
        initView();
    }

    private void initView() {

        tvTitle = findViewById(R.id.ll_header_content);

        RecyclerView recyclerView = findViewById(R.id.recycleView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RecycleAdapter adapter = new RecycleAdapter(mData);
        final View headView = getLayoutInflater().inflate(R.layout.head_layout, (ViewGroup) recyclerView.getParent(), false);
        adapter.addHeaderView(headView);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.e(TAG, "onItemClick: position:" + position);
            }
        });
        recyclerView.setAdapter(adapter);

        headView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                headView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mHeight = headView.getHeight() - tvTitle.getHeight();
                Log.e(TAG, "mHeight :" + mHeight+"  topHeight:"+headView.getHeight());
            }
        });


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int scollYDistance = getScollYDistance();
                if (scollYDistance < 0) {
                    return;
                }
                if (scollYDistance > mHeight) {
                    scollYDistance = mHeight;
                }
                float persent = scollYDistance * 1.0f / mHeight;

                float alpha = (255 * persent);//得到透明度

                tvTitle.setBackgroundColor(Color.argb((int) alpha, 48, 63, 159));

            }
        });

        findViewById(R.id.iv_header_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public int getScollYDistance() {
        int position = layoutManager.findFirstVisibleItemPosition();
        if (position != 0) {
            return -1;
        }
        View firstVisiableChildView = layoutManager.findViewByPosition(position);
        int height = -firstVisiableChildView.getTop();
        return height;
    }


}
