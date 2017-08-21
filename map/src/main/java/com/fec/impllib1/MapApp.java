package com.fec.impllib1;

import android.app.Application;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by XQ Yang on 2017/8/21  18:19.
 * Description :
 */

public class MapApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.init(this);
    }
}
