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
		// ������ʾ�Լ���λ��
		mBaiduMap.setMyLocationEnabled(true);

		LocationClientOption option = new LocationClientOption();
		// ָ����λ������ϵΪ�ٶȾ�γ������ϵ
		option.setCoorType("bd09ll"); 
		// �Ƿ���Ҫ���ص�ַ��Ϣ
		option.setIsNeedAddress(true);
		// ���ж�λ��ʽ��ȷ���Զ�ѡȡ���ֶ�λ��ʽ
		option.setLocationMode(LocationMode.Hight_Accuracy);
		// �����Ƿ���Ҫ��Ļ����
		option.setNeedDeviceDirect(false);
		option.setOpenGps(true);
		// ÿ������λ�ļ��ʱ�� ���ֵ�������1000����
		option.setScanSpan(5000);
		// ��λ��ʱʱ�� 3��
		option.setTimeOut(3000);
		mClient = new LocationClient(LocationActivity.this, option);
		// ע�ᶨλ������(������λ��Ľ��)
		mClient.registerLocationListener(locListener);
		// ����������λ
		mClient.start();
		mClient.requestLocation();
	}

	private BDLocationListener locListener = new BDLocationListener() {

		@Override
		public void onReceiveLocation(BDLocation loc) {
			Log.i("TAG", "��λ���ͣ�" + loc.getLocType());
			Log.i("TAG", "ά�ȣ�" + loc.getLatitude());
			Log.i("TAG", "���ȣ�" + loc.getLongitude());
			if (loc.getLocType() == 61 || loc.getLocType() == 161) {
				// ��ǰ����һ�򿪾ͽ���ע(��ͷ��)�ƶ�����λ�õ�������
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
					// ��ͼ���ƶ�����ǰ��γ��λ��
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
