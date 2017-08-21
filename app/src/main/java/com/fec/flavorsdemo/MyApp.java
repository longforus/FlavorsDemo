package com.fec.flavorsdemo;

import android.app.Application;
import com.fec.baselib.MapInf;
import com.fec.baselib.ServiceBus;
import com.fec.baselib.ShowToast;
import com.fec.impllib1.MapImpl;
import com.fec.impllib2.ShowToastImpl;

/**
 * Created by XQ Yang on 2017/8/21  16:18.
 * Description :
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        registerMyService();// TODO: 2017/8/21 服务的注册放在哪里?
    }


    private void registerMyService() {
        ServiceBus.register(MapInf.class,new MapImpl());
        ServiceBus.register(ShowToast.class,new ShowToastImpl());
    }
}
