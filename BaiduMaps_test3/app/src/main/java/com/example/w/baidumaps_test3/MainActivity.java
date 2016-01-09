package com.example.w.baidumaps_test3;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.location.Location;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

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
import com.example.w.baidumaps_test3.NpPoints;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;


public class MainActivity extends AppCompatActivity implements ConnectionCallbacks, OnConnectionFailedListener {
    static final String TAG_1 = "L_1";
    static final String TAG_XML = "L_XML";

    MapView mMapView = null;

    BaiduMap mBaiduMap = null;

    ArrayList<NpPoints> npPointsList = new ArrayList<NpPoints>();
    NpPoints npPoints_tmp = new NpPoints();

    GoogleApiClient mGoogleApiClient = null;
    Location mLastLocation = null;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        mMapView = (MapView) findViewById(R.id.bmapView);

        Resources res = this.getResources();

        /**
         * Import data from XML resource into an ArrayList
         */
        XmlResourceParser xpp = res.getXml(R.xml.points1);

        int eventType = 0;
        int line;
        try {
            eventType = xpp.getEventType();
            String tag_name = "";
            while (eventType != XmlPullParser.END_DOCUMENT) {
                line = xpp.getLineNumber();
                if (eventType == XmlPullParser.START_TAG) {
                    tag_name = xpp.getName();

                    if (tag_name.equals("np_poi")) {
                        npPoints_tmp = new NpPoints();
                    }
                    Log.d(TAG_XML, "hello " + xpp.getName());
                } else if (eventType == XmlPullParser.TEXT) {
                    Log.d(TAG_XML, "hello text " + xpp.getText() + " " + tag_name);

                    if (tag_name.equals("name")) {
                        npPoints_tmp.name = xpp.getText();
                        Log.d(TAG_XML, npPoints_tmp.name);
                    } else if (tag_name.equals("lat")) {
                        npPoints_tmp.lat = Double.valueOf(xpp.getText());
                    } else if (tag_name.equals("lng")) {
                        npPoints_tmp.lng = Double.valueOf(xpp.getText());
                    } else if (tag_name.equals("description")) {
                        npPoints_tmp.description = xpp.getText();
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    if (xpp.getName().equals("np_poi")) {
                        npPointsList.add(npPoints_tmp);
                    }
                    if (xpp.getName().equals("data")) {
                        ;
                    }
                }
                eventType = xpp.next();
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }

        /**
         * Display points from ArrayList on map
         */
        Iterator<NpPoints> itr = npPointsList.iterator();
        mBaiduMap = mMapView.getMap();

        while (itr.hasNext()) {
            npPoints_tmp = itr.next();

            LatLng point = new LatLng(npPoints_tmp.lat, npPoints_tmp.lng);
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.icon_marka);
            OverlayOptions option = new MarkerOptions().position(point).icon(bitmap);

            mBaiduMap = mMapView.getMap();
            mBaiduMap.addOverlay(option);
        }

        /**
         * Retrieve last known location using Google API
         */
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        if (mGoogleApiClient == null) {
            Log.d(TAG_1, "mGoogleApiClient is null");
        } else {
            Log.d(TAG_1, "MgoogleApiClient is not null");
        }


        /**
         * Center the map on location
         */
        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        // Center map at given coordinates
        MapStatus ms;
        ms = new MapStatus.Builder().target(new LatLng(31.221598610082765, 121.48234391526827)).zoom(12).build();
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(ms));
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.w.baidumaps_test3/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.w.baidumaps_test3/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
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

    @Override
    public void onConnected(Bundle connectionHint) {
        Log.d(TAG_1, "Location services connected");
        try {
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);
            if (mLastLocation != null) {
                Log.d(TAG_1, "Lat: " + String.valueOf(mLastLocation.getLatitude()));
                Log.d(TAG_1, "Long: " + String.valueOf(mLastLocation.getLongitude()));

                LatLng point = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
                BitmapDescriptor bitmap = BitmapDescriptorFactory
                        .fromResource(R.drawable.icon_markb);
                OverlayOptions option = new MarkerOptions().position(point).icon(bitmap);

                mBaiduMap = mMapView.getMap();
                mBaiduMap.addOverlay(option);
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
