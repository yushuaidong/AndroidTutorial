package com.chen.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import com.chen.androidtutorial.R;
import com.chen.service.BindService;
import com.chen.service.StartService;

public class StartServiceActivity extends Activity {

    private BindService.MyBinder myBinder;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myBinder = (BindService.MyBinder)iBinder;
            myBinder.DownloadFile();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_service);

        //一条龙服务
        this.findViewById(R.id.btnStartService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //启动服务
                Intent intent = new Intent(StartServiceActivity.this, StartService.class);
                startService(intent);
            }
        });

        this.findViewById(R.id.btnStopService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //停止服务
                Intent intent = new Intent(StartServiceActivity.this, StartService.class);
                stopService(intent);
            }
        });

        this.findViewById(R.id.btnBindService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //绑定服务
                Intent intent = new Intent(StartServiceActivity.this,BindService.class);
                bindService(intent,serviceConnection,BIND_AUTO_CREATE);
            }
        });

        this.findViewById(R.id.btnStopService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //取消绑定服务
                unbindService(serviceConnection);
            }
        });
    }
}
