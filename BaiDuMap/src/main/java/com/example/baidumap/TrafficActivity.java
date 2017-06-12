package com.example.baidumap;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRoutePlanOption;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.SuggestAddrInfo;
import com.baidu.mapapi.search.route.TransitRouteLine;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.example.baidumap.overlay.DrivingRouteOverlay;
import com.example.baidumap.overlay.OverlayManager;
import com.example.baidumap.overlay.TransitRouteOverlay;


public class TrafficActivity extends Activity {

    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private boolean mIsopen;
    private RoutePlanSearch mRouteSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic);
        mMapView = (MapView) findViewById(R.id.MapView);
        mBaiduMap = mMapView.getMap();
        // 普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

        // 卫星地图
        // mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
        // 是否支持热力图
        // mBaiduMap.setBaiduHeatMapEnabled(true);
        mRouteSearch = RoutePlanSearch.newInstance();
        mRouteSearch.setOnGetRoutePlanResultListener(listener);

    }

    private OnGetRoutePlanResultListener listener = new OnGetRoutePlanResultListener() {

        @Override
        public void onGetWalkingRouteResult(WalkingRouteResult arg0) {

        }

        @Override
        public void onGetTransitRouteResult(TransitRouteResult arg0) {
            // if (arg0.error == SearchResult.ERRORNO.NO_ERROR) {
            // List<TransitRouteLine> list = arg0.getRouteLines();
            // TransitRouteOverlay transOverlay = new TransitRouteOverlay(
            // mBaiduMap);
            // transOverlay.setData(list.get(0));
            // transOverlay.addToMap();
            // transOverlay.zoomToSpan();
            // } else {
            // Log.i("TAG", "======error=====");
            // }

        }

        @Override
        public void onGetDrivingRouteResult(DrivingRouteResult arg0) {
            if (arg0.error == SearchResult.ERRORNO.NO_ERROR) {
                List<DrivingRouteLine> list = arg0.getRouteLines();
                DrivingRouteOverlay driveOverlay = new DrivingRouteOverlay(
                        mBaiduMap);
                driveOverlay.setData(list.get(0));
                driveOverlay.addToMap();
                driveOverlay.zoomToSpan();
            } else if (arg0.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                SuggestAddrInfo info = arg0.getSuggestAddrInfo();

                List<PoiInfo> startListNode = info.getSuggestStartNode();
                for (PoiInfo c : startListNode) {
                    if (c != null) {
                        Log.i("TAG", c.city);
                        Log.i("TAG", c.name);
                    }
                }
                List<CityInfo> startList = info.getSuggestStartCity();
                for (CityInfo c : startList) {
                    if (c != null) {
                        Log.i("TAG", c.city);
                        Log.i("TAG", c.num + "");
                    }
                }
                List<CityInfo> endList = info.getSuggestEndCity();
                for (CityInfo c : endList) {
                    if (c != null) {
                        Log.i("TAG", c.city);
                        Log.i("TAG", c.num + "");
                    }
                }
            } else {
                Log.i("TAG", "======error=====" + arg0.error.toString());
            }
        }

        @Override
        public void onGetBikingRouteResult(BikingRouteResult arg0) {

        }
    };

    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_open_close:
                if (!mIsopen) {
                    mBaiduMap.setTrafficEnabled(true);
                    mIsopen = true;
                } else {
                    mBaiduMap.setTrafficEnabled(false);
                    mIsopen = false;
                }

                break;

            // 搜索路线
            case R.id.search:
                // 不要方到onCreate()方法中 否则搜索不到
                DrivingRoutePlanOption driveOption = new DrivingRoutePlanOption();
                PlanNode driveStart = PlanNode.withCityNameAndPlaceName("北京市",
                        "北京南站");
                driveOption.from(driveStart);
                PlanNode driveEnd = PlanNode.withCityNameAndPlaceName("北京市",
                        "北京吉利大学南门");
                driveOption.to(driveEnd);
                mRouteSearch.drivingSearch(driveOption);

                // mRouteSearch.walkingSearch(arg0);

                // TransitRoutePlanOption transOption = new
                // TransitRoutePlanOption();
                // PlanNode start = PlanNode.withCityNameAndPlaceName("北京", "天安门");
                // transOption.from(start);
                // PlanNode end = PlanNode
                // .withCityNameAndPlaceName("北京", "北京科技职业技术学院");
                // transOption.to(end);
                // transOption.city("北京");
                // mRouteSearch.transitSearch(transOption);

                break;
        }

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
