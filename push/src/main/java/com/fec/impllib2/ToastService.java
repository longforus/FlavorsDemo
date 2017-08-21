package com.fec.impllib2;

import com.fec.baselib.ShowToast;

/**
 * Created by XQ Yang on 2017/8/21  14:17.
 * Description :
 */

public class ToastService {
    public static ShowToast getShowServce() {
        return new ShowToastImpl();
    }
}
