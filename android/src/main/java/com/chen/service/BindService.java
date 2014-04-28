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

    private static final String TAG = "BindService";

    @Override
    public void onCreate() {
        Log.i(TAG,"BindService - > onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"BindService - > onStartCommand");//只有startService会调用
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.w(TAG,"BindService - > onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG,"BindService - > onDestroy");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    public class MyBinder extends Binder{
        public void StartDownload(){
            //模拟下载文件方法
            Log.i(TAG,"BinderService正在执行下载任务");
        }
    }
}
