package com.fec.baselib;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * Created by XQ Yang on 2017/8/21  14:59.
 * Description :
 */

public interface MapInf extends IProvider {
    String getdesc();

    MyMapView getMapView(FragmentManager manager,Context context, int parentID);

    void getLocation(Context context,MapLocationListener listener);

    void onDestroy();

    void location();

}
