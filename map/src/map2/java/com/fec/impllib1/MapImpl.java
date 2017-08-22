package com.fec.impllib1;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.fec.baselib.MapInf;
import com.fec.baselib.MapLocationListener;
import com.fec.baselib.MyMapView;

/**
 * Created by XQ Yang on 2017/8/21  15:02.
 * Description :
 */
@Route(path = "/map/impl")
public class MapImpl implements MapInf {

    private MyMapView mInstantiate;

    @Override
    public String getdesc() {
        return "高德地图";
    }

    @Override
    public MyMapView getMapView(FragmentManager manager, Context context, int parentID) {
        mInstantiate = (AmapMapFragment) Fragment.instantiate(context, AmapMapFragment.class.getName());
        manager.beginTransaction().add(parentID, (Fragment) mInstantiate).commit();
        return mInstantiate;
    }

    @Override
    public void getLocation(Context context, MapLocationListener listener) {
        if (ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION") == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(((Activity) context),
                new String[] { "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", Manifest.permission.READ_PHONE_STATE }, 0);
        }
        mInstantiate.addLocationListener(listener);
    }

    @Override
    public void onDestroy() {
    mInstantiate.onDestroy();
    }

    @Override
    public void location() {
        mInstantiate.location(null);
    }

    @Override
    public void init(Context context) {

    }
}
