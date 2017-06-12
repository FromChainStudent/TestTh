package com.example.baidumap.lbs;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.baidu.mapapi.cloud.CloudListener;
import com.baidu.mapapi.cloud.CloudManager;
import com.baidu.mapapi.cloud.CloudPoiInfo;
import com.baidu.mapapi.cloud.CloudSearchResult;
import com.baidu.mapapi.cloud.DetailSearchResult;
import com.baidu.mapapi.cloud.NearbySearchInfo;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.example.baidumap.R;

public class LBSSearchActivity extends Activity {

	private MapView mMapView;
	private BaiduMap mBaiduMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mMapView = (MapView) findViewById(R.id.MapView);
		mBaiduMap = mMapView.getMap();
		// 普通地图
		mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

		// 卫星地图
		// mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
		// 是否支持热力图
		// mBaiduMap.setBaiduHeatMapEnabled(true);
		CloudManager.getInstance().init(listener);

	}

	public void click(View view) {
		NearbySearchInfo info = new NearbySearchInfo();
		info.ak = "q5nQ7ggEFppDzMnfhokEXQfZjd8rarAv";
		info.geoTableId = 138403;
		info.location = "116,41";
		info.radius = 1000000;
		info.tags = "小吃";
		CloudManager.getInstance().nearbySearch(info);

	}

	private CloudListener listener = new CloudListener() {

		@Override
		public void onGetSearchResult(CloudSearchResult result, int arg1) {
			if (arg1 == 0) {
				List<CloudPoiInfo> list = result.poiList;
				for (CloudPoiInfo info : list) {
					Log.i("TAG", info.tags);
					Log.i("TAG", info.title);
					Log.i("TAG", info.distance+"");
				}

			}
		}

		@Override
		public void onGetDetailSearchResult(DetailSearchResult arg0, int arg1) {

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
