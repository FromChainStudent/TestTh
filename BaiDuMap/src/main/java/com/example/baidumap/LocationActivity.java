package com.example.baidumap;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import android.os.Bundle;
import android.util.Log;
import android.app.Activity;

public class LocationActivity extends Activity {

	private MapView mMapView;
	private LocationClient mClient;
	private BaiduMap mBaiduMap;
	private boolean mIsFirst = true;
	private LatLng latlng;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);
		mMapView = (MapView) findViewById(R.id.MapView);
		mBaiduMap = mMapView.getMap();
		// 允许显示自己的位置
		mBaiduMap.setMyLocationEnabled(true);

		LocationClientOption option = new LocationClientOption();
		// 指定定位的坐标系为百度经纬度坐标系
		option.setCoorType("bd09ll"); 
		// 是否需要返回地址信息
		option.setIsNeedAddress(true);
		// 哪中定位方式精确就自动选取哪种定位方式
		option.setLocationMode(LocationMode.Hight_Accuracy);
		// 设置是否需要屏幕方向
		option.setNeedDeviceDirect(false);
		option.setOpenGps(true);
		// 每次请求定位的间隔时间 这个值必须大于1000毫秒
		option.setScanSpan(5000);
		// 定位超时时间 3秒
		option.setTimeOut(3000);
		mClient = new LocationClient(LocationActivity.this, option);
		// 注册定位监听器(监听定位后的结果)
		mClient.registerLocationListener(locListener);
		// 开启并请求定位
		mClient.start();
		mClient.requestLocation();
	}

	private BDLocationListener locListener = new BDLocationListener() {

		@Override
		public void onReceiveLocation(BDLocation loc) {
			Log.i("TAG", "定位类型：" + loc.getLocType());
			Log.i("TAG", "维度：" + loc.getLatitude());
			Log.i("TAG", "经度：" + loc.getLongitude());
			if (loc.getLocType() == 61 || loc.getLocType() == 161) {
				// 当前程序一打开就将标注(大头针)移动到定位好的坐标上
				MyLocationData locationData = new MyLocationData.Builder()
						.accuracy(loc.getRadius()).latitude(loc.getLatitude())
						.longitude(loc.getLongitude()).speed(loc.getSpeed())
						.build();
				mBaiduMap.setMyLocationData(locationData);
				if (mIsFirst) {
					mIsFirst = false;
					latlng = new LatLng(loc.getLatitude(), loc.getLongitude());
					MapStatusUpdate update = MapStatusUpdateFactory
							.newLatLng(latlng);
					// 将图标移动到当前经纬度位置
					mBaiduMap.animateMapStatus(update);
				}
			}

		}
	};

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
