package com.fec.impllib1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fec.baselib.MapInf;
import com.fec.baselib.MapView;

public class MapTestActivity extends AppCompatActivity {

    private MapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_test);
        //final MapInf mapInf = ServiceBus.getService(MapInf.class);
        //mapInf.init(this);

        final MapInf mapInf = (MapInf) ARouter.getInstance().build("/map/impl").navigation();
        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMapView = mapInf.getMapView(getSupportFragmentManager(), MapTestActivity.this, R.id.fl);
            }
        });


    }
}
