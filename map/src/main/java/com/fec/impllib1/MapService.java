package com.fec.impllib1;

import com.fec.baselib.MapInf;

/**
 * Created by XQ Yang on 2017/8/21  15:00.
 * Description :
 */

public class MapService {
    public static MapInf getMap() {
        return new MapImpl();
    }
}
