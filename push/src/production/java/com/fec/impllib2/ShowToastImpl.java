package com.fec.impllib2;

import android.content.Context;
import android.widget.Toast;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.fec.baselib.ShowToast;

/**
 * Created by XQ Yang on 2017/8/21  11:34.
 * Description :
 */
@Route(path = "/toast/toast")
public class ShowToastImpl implements ShowToast {

    @Override
    public void show(Context context) {
        Toast.makeText(context,"小米推送",Toast.LENGTH_LONG).show();
    }

    @Override
    public void init(Context context) {

    }
}
