package com.fec.diary;

import android.app.Application;
import com.fec.baselib.MapInf;
import com.fec.baselib.ServiceBus;
import com.fec.baselib.ShowToast;
import com.fec.impllib1.MapImpl;
import com.fec.impllib2.ShowToastImpl;

/**
 * Created by XQ Yang on 2017/8/21  17:16.
 * Description :
 */

public class DiaryApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ServiceBus.register(MapInf.class,new MapImpl());
        ServiceBus.register(ShowToast.class,new ShowToastImpl());
    }
}
