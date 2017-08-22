package com.fec.impllib1;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
        LocationClient mLocClient = new LocationClient(context);
        MyLocationListenner locationListener = new MyLocationListenner(listener,mLocClient);
        mLocClient.registerLocationListener(locationListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();
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
            mLocClient.unRegisterLocationListener(this);
        }
    }
}
