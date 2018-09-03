package com.ifreecomm.titlebargradualchange.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ScrollView;

/**
 * Created by IT小蔡 on 2018-8-31.
 */

public class ObservableScrollView extends ScrollView {
    private static final String TAG = "ObservableScrollView";

    private OnObservableScrollViewListener observerScrollViewListener;

    public ObservableScrollView(Context context) {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public interface OnObservableScrollViewListener {
        void onObservableScrollViewListener(int l, int t, int oldl, int oldt);
    }

    public void setOnObservableScrollViewListener(OnObservableScrollViewListener observerScrollViewListener) {
        this.observerScrollViewListener = observerScrollViewListener;
    }

    /**
     *
     * @param l
     * @param t 当前滚动距离（距离x轴）
     * @param oldl
     * @param oldt 上一次滚动距离
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        Log.e(TAG, "onScrollChanged: l:" + l + " t:" + t + " oldl:" + oldl + "   oldt:" + oldt);
        if (observerScrollViewListener != null) {
            observerScrollViewListener.onObservableScrollViewListener(l, t, oldl, oldt);
        }
    }
}
