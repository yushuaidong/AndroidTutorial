package com.chen.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * Time: 16:25
 * Info:
 */
public class BindService extends Service {

    private MyBinder myBinder = new MyBinder();

    @Override
    public void onCreate() {
        Log.w("BindService","BindService->onCreate()");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.w("BindService","BindService->onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.w("BindService","BindService->onDestroy()");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    public class MyBinder extends Binder{
        //下载文件
        public void DownloadFile(){
            Log.w("BindService","正在下载文件...");
        }
    }
}
