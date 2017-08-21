package com.fec.diary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import com.fec.baselib.ServiceBus;
import com.fec.baselib.ShowToast;

public class DiaryActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnPush;
    private Button btnMap;
    private FrameLayout fl;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-08-21 17:10:38 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        btnPush = (Button) findViewById(R.id.btn_push);
        btnMap = (Button) findViewById(R.id.btn_map);
        fl = (FrameLayout) findViewById(R.id.fl);

        btnPush.setOnClickListener(this);
        btnMap.setOnClickListener(this);
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-08-21 17:10:38 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if (v == btnPush) {
            ShowToast st = ServiceBus.getService(ShowToast.class);//使用服务总线进行注册 完全不涉及实现类
            if (st!=null) {  //有注册就有服务  没有注册的服务不得以运行
                st.show(this);
            }
        } else if (v == btnMap) {
            // Handle clicks for btnMap
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        findViews();
    }
}
