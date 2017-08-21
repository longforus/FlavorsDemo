package com.fec.flavorsdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import com.fec.baselib.MapInf;
import com.fec.baselib.ServiceBus;
import com.fec.baselib.ShowToast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ShowToast st = ToastService.getShowServce();
        ShowToast st = ServiceBus.getService(ShowToast.class);//使用服务总线进行注册 完全不涉及实现类
        if (st!=null) {  //有注册就有服务  没有注册的服务不得以运行
            st.show(this);
        }

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MapInf mapInf = MapService.getMap();
                MapInf mapInf = ServiceBus.getService(MapInf.class);
                if (mapInf!=null) {
                    Toast.makeText(MainActivity.this, mapInf.getdesc(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
