package com.fec.diary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fec.baselib.MapData;
import com.fec.baselib.MapInf;
import com.fec.baselib.MapLocationListener;
import com.fec.baselib.ShowToast;

public class DiaryActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnPush;
    private Button btnMap;
    private Button btnShow;
    private Button btnDw;
    private TextView textInfo;
    private FrameLayout fl;
    private MapInf mMapInf;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-08-22 16:39:13 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        btnPush = (Button)findViewById( R.id.btn_push );
        btnMap = (Button)findViewById( R.id.btn_map );
        btnShow = (Button)findViewById( R.id.btn_show );
        btnDw = (Button)findViewById( R.id.btn_dw );
        textInfo = (TextView)findViewById( R.id.text_Info );
        fl = (FrameLayout)findViewById( R.id.fl );

        btnPush.setOnClickListener( this );
        btnMap.setOnClickListener( this );
        btnShow.setOnClickListener( this );
        btnDw.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-08-22 16:39:13 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == btnPush ) {
            ShowToast navigation = (ShowToast) ARouter.getInstance().build("/toast/toast").navigation();
            //ShowToast navigation = (ShowToast) ARouter.getInstance().navigation(ShowToast.class);
            if (navigation != null) {
                navigation.show(DiaryActivity.this);
            } else {
                //navigation = ServiceBus.getService(ShowToast.class);
                //navigation.show(DiaryActivity.this);
            }
        } else if ( v == btnMap ) {
            mMapInf = (MapInf) ARouter.getInstance().build("/map/impl").navigation();
            textInfo.setText(mMapInf.getdesc());
        } else if ( v == btnShow ) {
            mMapInf.getMapView(getSupportFragmentManager(), DiaryActivity.this, R.id.fl);
            textInfo.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mMapInf.getLocation(DiaryActivity.this, new MapLocationListener() {
                        @Override
                        public void onReceive(MapData mapData) {
                            textInfo.setText(mapData.desc);
                        }
                    });
                }
            }, 500);
        } else if ( v == btnDw ) {
            mMapInf.location();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        findViews();
    }
}
