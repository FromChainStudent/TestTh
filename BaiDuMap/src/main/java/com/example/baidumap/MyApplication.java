package com.example.baidumap;

import org.xutils.x;

import com.baidu.mapapi.SDKInitializer;

import android.app.Application;

public class MyApplication extends Application {
	@Override
	public void onCreate() {
		// ��ʼ���ٶȵ�ͼSDK
		SDKInitializer.initialize(getApplicationContext());
		x.Ext.init(this);
		super.onCreate();
	}

}
