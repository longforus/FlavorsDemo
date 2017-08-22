package com.fec.impllib1;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.fec.baselib.MapData;
import com.fec.baselib.MapInf;
import com.fec.baselib.MapLocationListener;
import com.fec.baselib.MyMapView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by XQ Yang on 2017/8/21  15:02.
 * Description :
 */

@Route(path = "/map/impl")
public class MapImpl implements MapInf {

    private MyLocationListenner mLocationListener;
    private LocationClient mMLocClient;
    private Context mContext;
    private MyMapView mInstantiate;

    @Override
    public String getdesc() {
        return "百度地图";
    }

    @Override
    public void init(Context context) {
        mContext = context;
        SDKInitializer.initialize(context.getApplicationContext());
    }

    @Override
    public MyMapView getMapView(FragmentManager manager, Context context, int parentID) {
        mInstantiate = (BaiduMapFragment) Fragment.instantiate(context, BaiduMapFragment.class.getName());
        manager.beginTransaction().add(parentID, (Fragment) mInstantiate).commit();

        return mInstantiate;
    }

    @Override
    public void getLocation(Context context, MapLocationListener listener) {
        if (ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION") == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(((Activity) context),
                new String[] { "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", Manifest.permission.READ_PHONE_STATE }, 0);
        }
        mMLocClient = new LocationClient(context.getApplicationContext());
        mLocationListener = new MyLocationListenner(listener, context);
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
        mInstantiate.onDestroy();
    }

    @Override
    public void location() {
        getLocation(mContext, new MapLocationListener() {
            @Override
            public void onReceive(MapData mapData) {
                mInstantiate.location(mapData);
            }
        });
    }

    /**
     * 定位SDK监听函数, 需实现BDLocationListener里的方法
     */
    public static class MyLocationListenner implements BDLocationListener {
        private MapLocationListener listener;
        private Context mContext;

        public MyLocationListenner(MapLocationListener listener, Context mContext) {
            this.listener = listener;
            this.mContext = mContext;
        }

        @Override
        public void onReceiveLocation(final BDLocation location) {
            Observable.create(new ObservableOnSubscribe<MapData>() {
                @Override
                public void subscribe(@NonNull ObservableEmitter<MapData> e) throws Exception {
                    Log.i(TAG, "onReceiveLocation: " + Thread.currentThread().getName());
                    MapData mapData = new MapData();
                    mapData.desc = location.getAddrStr();
                    mapData.latitude = location.getLatitude();
                    mapData.longitude = location.getLongitude();
                    mapData.radius = location.getRadius();
                    e.onNext(mapData);
                }
            }).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<MapData>() {
                @Override
                public void accept(MapData data) throws Exception {
                    listener.onReceive(data);
                }
            });
            //mLocClient.unRegisterLocationListener(this);
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    }

    private static final String TAG = "MapImpl";
}
