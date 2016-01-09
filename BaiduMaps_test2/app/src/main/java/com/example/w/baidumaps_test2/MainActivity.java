package com.example.w.baidumaps_test2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;


public class MainActivity extends AppCompatActivity {

    MapView mMapView = null;

    BaiduMap mBaiduMap = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.activity_main);
        mMapView = (MapView) findViewById(R.id.bmapView);

        // point A
        LatLng point = new LatLng(31.221598610082765, 121.48234391526827);
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_marka);

        OverlayOptions option = new MarkerOptions().position(point).icon(bitmap);

        mBaiduMap = mMapView.getMap();
        mBaiduMap.addOverlay(option);

        // point B
        point = new LatLng(31.213306624571516, 121.59113320405415);
        bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_markb);

        option = new MarkerOptions().position(point).icon(bitmap);

        mBaiduMap.addOverlay(option);

        // point C
        point = new LatLng(31.22688301648287, 121.47622196321777);
        bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_markc);

        option = new MarkerOptions().position(point).icon(bitmap);

        mBaiduMap.addOverlay(option);

        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        // Center map at given coordinates
        MapStatus ms;
        ms = new MapStatus.Builder().target(new LatLng(31.221598610082765, 121.48234391526827)).zoom(12).build();
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(ms));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
}
