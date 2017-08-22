package com.fec.impllib1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.fec.baselib.MapData;
import com.fec.baselib.MapView;

/**
 * Created by XQ Yang on 2017/8/21  18:05.
 * Description :
 */

public class BaiduMapFragment extends Fragment implements MapView {

    private com.baidu.mapapi.map.MapView mMapView;
    private LocationClient mLocClient;
    private BaiduMap mBaiduMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_baidu_map,null);
        mMapView = view.findViewById(R.id.mv);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void location(MapData data) {
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 构造定位数据
        MyLocationData locData = new MyLocationData.Builder()
            .accuracy(data.radius)
            // 此处设置开发者获取到的方向信息，顺时针0-360
            .direction(100).latitude(data.latitude)
            .longitude(data.longitude).build();
        // 设置定位数据
        mBaiduMap.setMyLocationData(locData);
        // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.icon_geo);
        MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, mCurrentMarker);
        mBaiduMap.setMyLocationConfiguration(config);
        // 当不需要定位图层时关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);

    }
}
