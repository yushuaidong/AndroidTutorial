package com.chen.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.chen.androidtutorial.R;
import com.chen.service.BindService;
import com.chen.service.StartAndBindService;
import com.chen.service.StartService;

public class StartServiceActivity extends Activity {

    private BindService.MyBinder myBinder;

    //解决Service not registered
    private boolean isBind = false;

    private Button btnStart,btnStop,btnBind,btnUnBind;
    private ButtonListener listener;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myBinder = (BindService.MyBinder) iBinder;
            myBinder.StartDownload();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_service);

        listener = new ButtonListener();

        //一条龙服务
        this.findViewById(R.id.btnStartService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //启动服务
                Intent intent = new Intent(StartServiceActivity.this, StartService.class);
                Log.i("StartServiceActivity", "StartServiceActivity - > " + Thread.currentThread().getId());
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
                Intent intent = new Intent(StartServiceActivity.this, BindService.class);
                isBind = bindService(intent, serviceConnection, BIND_AUTO_CREATE);
            }
        });

        this.findViewById(R.id.btnUnbindService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //取消绑定服务
                if (isBind) {
                    unbindService(serviceConnection);
                    isBind = false;
                }
            }
        });

        btnStart = (Button)findViewById(R.id.btnStart_StartBindService);
        btnStop = (Button)findViewById(R.id.btnStop_StartBindService);
        btnBind = (Button)findViewById(R.id.btnBind_StartBindService);
        btnUnBind = (Button)findViewById(R.id.btnUnBind_StartBindService);

        btnStart.setOnClickListener(listener);
        btnStop.setOnClickListener(listener);
        btnBind.setOnClickListener(listener);
        btnUnBind.setOnClickListener(listener);
    }

    private class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.btnStart_StartBindService:
                    Intent intent = new Intent(StartServiceActivity.this, StartAndBindService.class);
                    startService(intent);
                    break;
                case R.id.btnStop_StartBindService:
                    Intent stopIntent = new Intent(StartServiceActivity.this, StartAndBindService.class);
                    stopService(stopIntent);
                    break;
                case R.id.btnBind_StartBindService:
                    Intent bindIntent = new Intent(StartServiceActivity.this, StartAndBindService.class);
                    isBind = bindService(bindIntent,conn,BIND_AUTO_CREATE);
                    break;
                case R.id.btnUnBind_StartBindService:
                    unbindService(conn);
                    break;
            }
        }
    }

    private ServiceConnection conn  = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            StartAndBindService.MyBinderClass myBinderClass = (StartAndBindService.MyBinderClass)iBinder;
            myBinderClass.Show();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
}
