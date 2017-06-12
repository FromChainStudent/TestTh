package com.example.baidumap;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;


public class PoiSearchActivity extends Activity {

	private MapView mMapView;
	private PoiSearch mPoiSearch;
	private EditText mEt_poi;
	private BitmapDescriptor mBDescriptor;
	private BaiduMap mBaiduMap;
	private InfoWindow mInfoWindow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_poi_search);
		mMapView = (MapView) findViewById(R.id.MapView);
		mBaiduMap = mMapView.getMap();

		mEt_poi = (EditText) findViewById(R.id.et_poi);
		mPoiSearch = PoiSearch.newInstance();
		mBDescriptor = BitmapDescriptorFactory
				.fromResource(R.drawable.ic_launcher);
		mBaiduMap.setOnMarkerClickListener(listener);

	}

	private OnMarkerClickListener listener = new OnMarkerClickListener() {
		@Override
		public boolean onMarkerClick(Marker marker) {
			Bundle bundle = marker.getExtraInfo();
			String name = bundle.getString("name");
			String address = bundle.getString("address");
			LatLng latLng = new LatLng(bundle.getDouble("lat"),
					bundle.getDouble("lng"));
			Button button = new Button(PoiSearchActivity.this);
			button.setText(name + "====" + address);
			mInfoWindow = new InfoWindow(button, latLng, -40);
			mBaiduMap.showInfoWindow(mInfoWindow);
			return true;
		}
	};

	public void search(View view) {
		// �ڱ༭�������������Ȥ��
		String poiStr = mEt_poi.getText().toString().trim();
		PoiCitySearchOption options = new PoiCitySearchOption();
		options.city("����");
		options.keyword(poiStr);
		mPoiSearch.searchInCity(options);
		// ������Ȥ���������
		mPoiSearch.setOnGetPoiSearchResultListener(getPoiListener);

		// ��������λ�ø�������Ȥ��
		// PoiNearbySearchOption options2=new PoiNearbySearchOption();
		// options2.location(arg0);
		// options2.keyword(arg0);
		// options2.radius(); //��λ����
		// mPoiSearch.searchNearby(options2);
		// mPoiSearch.setOnGetPoiSearchResultListener(arg0);

	}

	private OnGetPoiSearchResultListener getPoiListener = new OnGetPoiSearchResultListener() {
		// �õ���Ȥ����
		@Override
		public void onGetPoiResult(PoiResult poiResult) {
			// �õ�������������е���Ȥ��
			List<PoiInfo> list = poiResult.getAllPoi();
			// ��Ӹ�����֮ǰ����յ�ͼ�ϵĸ�����
			mBaiduMap.clear();
			for (PoiInfo info : list) {
				if (info != null) {
					Log.i("TAG", info.city + "");
					Log.i("TAG", info.address + "");
					Log.i("TAG", info.name + "");
					Log.i("TAG", info.phoneNum + "");
					Log.i("TAG", info.location + "");
					Bundle bundle = new Bundle();
					bundle.putString("name", info.name);
					bundle.putString("address", info.address);
					bundle.putDouble("lat", info.location.latitude);
					bundle.putDouble("lng", info.location.longitude);
					OverlayOptions option = new MarkerOptions()
							.position(info.location).title(info.name)
							.icon(mBDescriptor).extraInfo(bundle);
					mBaiduMap.addOverlay(option);
				}
			}
		}

		// �õ���Ȥ�����ϸ��Ϣ
		@Override
		public void onGetPoiDetailResult(PoiDetailResult arg0) {

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
		mPoiSearch.destroy();
		mMapView.onDestroy();
		super.onDestroy();
	}

}
