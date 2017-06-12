package com.example.vpdemo;

import android.util.Log;

/**
 * Created by YC on 2017/6/1.
 */

public class FirstFragment extends BaseFragment {
    @Override
    protected void initPrepare() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void initData() {
        Log.i("初始化", "start");
    }

    @Override
    protected int layoutView() {
        return R.layout.fragment_first;
    }
}
