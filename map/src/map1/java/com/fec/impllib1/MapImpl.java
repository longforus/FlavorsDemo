package com.fec.impllib1;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.fec.baselib.MapData;
import com.fec.baselib.MapInf;
import com.fec.baselib.MapLocationListener;
import com.fec.baselib.MapView;

/**
 * Created by XQ Yang on 2017/8/21  15:02.
 * Description :
 */

@Route(path = "/map/impl")
public class MapImpl implements MapInf {

    private MyLocationListenner mLocationListener;
    private LocationClient mMLocClient;

    @Override
    public String getdesc() {
        return "百度地图";
    }

    @Override
    public void init(Context context) {
        SDKInitializer.initialize(context.getApplicationContext());
    }

    @Override
    public MapView getMapView(FragmentManager manager,Context context, int parentID) {
        BaiduMapFragment instantiate = (BaiduMapFragment) Fragment.instantiate(context, BaiduMapFragment.class.getName());
        manager.beginTransaction().add(parentID, instantiate).commit();

        return instantiate;
    }

    @Override
    public void getLocation(Context context, MapLocationListener listener) {
        if (ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION") == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(((Activity) context),new String[]{"android.permission.ACCESS_COARSE_LOCATION","android.permission.ACCESS_FINE_LOCATION"},0);
        }
        mMLocClient = new LocationClient(context.getApplicationContext());
        mLocationListener = new MyLocationListenner(listener, mMLocClient);
        mMLocClient.registerLocationListener(mLocationListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mMLocClient.setLocOption(option);
        mMLocClient.start();
    }

    @Override
    public void onDestroy() {
        mMLocClient.stop();
    }

    /**
     * 定位SDK监听函数, 需实现BDLocationListener里的方法
     */
    public static class MyLocationListenner implements BDLocationListener {
       private MapLocationListener listener;
       private LocationClient mLocClient;

        public MyLocationListenner(MapLocationListener listener, LocationClient mLocClient) {
            this.listener = listener;
            this.mLocClient = mLocClient;
        }

        @Override
        public void onReceiveLocation(BDLocation location) {
            MapData mapData = new MapData();
            mapData.desc = location.getAddrStr();
            mapData.latitude = location.getLatitude();
            mapData.longitude = location.getLongitude();
            mapData.radius = location.getRadius();
            listener.onReceive(mapData);
            //mLocClient.unRegisterLocationListener(this);
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    }
}
