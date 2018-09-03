package com.ifreecomm.titlebargradualchange.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ifreecomm.titlebargradualchange.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by IT小蔡 on 2018-9-3.
 */

public class RecycleAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public RecycleAdapter(@Nullable String[] data) {
        super(R.layout.main_list_adapter, new ArrayList<String>(Arrays.asList(data)));
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.text,item);
    }
}
