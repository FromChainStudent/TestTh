package com.example.baidumap;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;

import android.os.Bundle;
import android.app.Activity;

public class MainActivity extends Activity {

	private MapView mMapView;
	private BaiduMap mBaiduMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mMapView = (MapView) findViewById(R.id.MapView);
		mBaiduMap = mMapView.getMap();
		// ��ͨ��ͼ
		mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

		// ���ǵ�ͼ
		// mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
		// �Ƿ�֧������ͼ
		// mBaiduMap.setBaiduHeatMapEnabled(true);

		// �Ƿ�֧�ֽ�ͨͼ
		mBaiduMap.setTrafficEnabled(true);

	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		super.onResume();
	}

	@Override
	protected void onPause() {
		mMapView.onPause();
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		mMapView.onDestroy();
		super.onDestroy();
	}

}
