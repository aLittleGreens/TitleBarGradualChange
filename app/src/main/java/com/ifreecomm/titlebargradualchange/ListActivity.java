package com.ifreecomm.titlebargradualchange;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.ifreecomm.titlebargradualchange.utils.StatusbarUtils;
import com.ifreecomm.titlebargradualchange.widget.ObservableListView;

public class ListActivity extends AppCompatActivity implements ObservableListView.OnObservableListViewListener {
    private static final String TAG = "ListActivity";
    private ObservableListView listView;
    String[] mData = {"a", "b", "d", "e", "f", "g", "h", "h", "a", "b", "d", "e", "f", "g", "h", "h", "a", "b", "d", "e", "f", "g", "h", "h", "a", "b", "d", "e", "f", "g", "h", "h"};
    private LinearLayout mHeaderContent;
    private int mHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置透明状态栏
        StatusbarUtils.enableTranslucentStatusbar(this);
        setContentView(R.layout.activity_list);
        initView();
    }

    private void initView() {

        listView = (ObservableListView) findViewById(R.id.listView);
        mHeaderContent = (LinearLayout) findViewById(R.id.ll_header_content);

//        final View headView = View.inflate(this, R.layout.head_layout, null);//移除了父容器layoutParams，会展示图片原始大小
        final View headView = getLayoutInflater().inflate(R.layout.head_layout,  listView, false);
        listView.addHeaderView(headView);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mData);
        listView.setAdapter(arrayAdapter);

        headView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                headView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mHeight = headView.getHeight() - mHeaderContent.getHeight();
                Log.e(TAG, "mHeight :" + mHeight);
            }
        });
        listView.setOnObservableListViewListener(this);

        findViewById(R.id.iv_header_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onObservableListViewListener(float t) {
        if (t < 0) {
            //已经划过头了
            return;
        }

        if (t > mHeight) {
            t = mHeight;
        }

        float persent = t * 1.0f / mHeight;

        float alpha = (255 * persent);//得到透明度
        mHeaderContent.setBackgroundColor(Color.argb((int) alpha, 48, 63, 159));
    }
}
