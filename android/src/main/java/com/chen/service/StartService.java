package com.chen.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * Time: 17:03
 * Info:
 */
public class StartService extends Service {

    private static final String TAG = "StartService:";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.w(TAG,"StartService->onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"StartService->StartCommand()");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        Log.e(TAG,"StartService->onDestroy()");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
