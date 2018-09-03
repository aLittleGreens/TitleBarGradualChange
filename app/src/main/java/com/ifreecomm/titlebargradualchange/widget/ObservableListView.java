package com.ifreecomm.titlebargradualchange.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

/**
 * Created by IT小蔡 on 2018-8-31.
 */

public class ObservableListView extends ListView {
    private static final String TAG = "ObservableListView";
    private OnObservableListViewListener observerListViewListener;

    public ObservableListView(Context context) {
        super(context);
    }

    public ObservableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public interface OnObservableListViewListener {
        void onObservableListViewListener(float t);
    }

    public void setOnObservableListViewListener(OnObservableListViewListener observerListViewListener) {
        this.observerListViewListener = observerListViewListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
//        Log.e(TAG, "onScrollChanged: l:" + l + " t:" + t + " oldl:" + oldl + "   oldt:" + oldt);
        if (observerListViewListener != null) {
            //获得y轴滑动距离
            float scaleY = getListViewY();
            Log.e(TAG, "scaleY-->" + scaleY);
            observerListViewListener.onObservableListViewListener(scaleY);
        }

    }

    private float getListViewY() {
        int firstVisiblePosition = this.getFirstVisiblePosition();
        if (firstVisiblePosition != 0) {    //超过头部，不再监听
            return -1;
        }
        View view = this.getChildAt(0);
        if (view == null) {
            return -1;
        }

        int top = view.getTop();//，往上移动，头部被遮盖部分变大，top是负值，所以取反
        return -top;
    }

}
