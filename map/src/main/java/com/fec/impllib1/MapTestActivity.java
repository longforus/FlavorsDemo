package com.fec.impllib1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fec.baselib.MapData;
import com.fec.baselib.MapInf;
import com.fec.baselib.MapLocationListener;
import com.fec.baselib.MapView;

public class MapTestActivity extends AppCompatActivity {

    private MapView mMapView;
    private MapInf mMapInf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_test);
        //final MapInf mapInf = ServiceBus.getService(MapInf.class);
        //mapInf.init(this);


        mMapInf = (MapInf) ARouter.getInstance().build("/map/impl").navigation();
        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMapView = mMapInf.getMapView(getSupportFragmentManager(), MapTestActivity.this, R.id.fl);
            }
        });
        findViewById(R.id.btn_dw).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMapInf.getLocation(MapTestActivity.this, new MapLocationListener() {
                    @Override
                    public void onReceive(MapData mapData) {
                        Toast.makeText(MapTestActivity.this, mapData.desc, Toast.LENGTH_SHORT).show();
                        mMapView.location(mapData);
                    }
                });
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        mMapInf.onDestroy();
    }
}
