package com.fec.impllib1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fec.baselib.MapData;
import com.fec.baselib.MapInf;
import com.fec.baselib.MapLocationListener;

public class MapTestActivity extends AppCompatActivity {

    private MapInf mMapInf;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_test);
        //final MapInf mapInf = ServiceBus.getService(MapInf.class);
        //mapInf.init(this);
        mTv = (TextView) findViewById(R.id.text_Info);

        mMapInf = (MapInf) ARouter.getInstance().build("/map/impl").navigation();
        mTv.setText(mMapInf.getdesc());

        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMapInf.getMapView(getSupportFragmentManager(), MapTestActivity.this, R.id.fl);
                mTv.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mMapInf.getLocation(MapTestActivity.this, new MapLocationListener() {
                            @Override
                            public void onReceive(MapData mapData) {
                                mTv.setText(mapData.desc);
                            }
                        });
                    }
                }, 500);
            }
        });
        findViewById(R.id.btn_dw).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mMapInf.getLocation(MapTestActivity.this, new MapLocationListener() {
                //    @Override
                //    public void onReceive(MapData mapData) {
                //        Toast.makeText(MapTestActivity.this, mapData.desc, Toast.LENGTH_SHORT).show();
                //        mMapView.location(mapData);
                //    }
                //});
                mMapInf.location();
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapInf.onDestroy();
    }
}
