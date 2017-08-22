package com.fec.baselib;

/**
 * Created by XQ Yang on 2017/8/21  17:56.
 * Description :
 */

public interface MyMapView {
    void location(MapData data);

    void onDestroy();

    void addLocationListener(MapLocationListener listener);
}
