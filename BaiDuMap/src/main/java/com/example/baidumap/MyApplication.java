package com.example.baidumap;

import org.xutils.x;

import com.baidu.mapapi.SDKInitializer;

import android.app.Application;

public class MyApplication extends Application {
	@Override
	public void onCreate() {
		// 初始化百度地图SDK
		SDKInitializer.initialize(getApplicationContext());
		x.Ext.init(this);
		super.onCreate();
	}

}
