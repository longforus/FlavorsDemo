package com.fec.diary;

import android.app.Application;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by XQ Yang on 2017/8/21  17:16.
 * Description :
 */

public class DiaryApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //ServiceBus.register(MapInf.class,new MapImpl());
        //ServiceBus.register(ShowToast.class,new ShowToastImpl());
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);

    }
}
