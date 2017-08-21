package com.fec.impllib1;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.baidu.mapapi.SDKInitializer;
import com.fec.baselib.MapInf;
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
}
