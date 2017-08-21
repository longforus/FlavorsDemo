package com.fec.baselib;

import android.support.v4.util.ArrayMap;
import java.util.Map;

/**
 * Created by XQ Yang on 2017/8/21  14:30.
 * Description :
 */

public class ServiceBus {
    private static Map<Class,Object> sMap;

    public static <T>  void register(Class<T> clazz, T object) {
        if (sMap == null) {
            sMap = new ArrayMap<>();
        }
        sMap.put(clazz, object);
    }

    public static<T>  T getService(Class<T> tClass) {
        return (T) sMap.get(tClass);
    }
}
