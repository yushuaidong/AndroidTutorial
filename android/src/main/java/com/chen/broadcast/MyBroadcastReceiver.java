package com.chen.broadcast;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * Time: 20:50
 * Info:
 * <p/>
 * Broadcast Receiver 顾名思义“广播接收器” — 可以接收来自系统和应用的广播。
 * <p/>
 * 1、如何创建Broadcast Receiver
 * <p/>
 * a、新建一个继承BroadcastReceiver的类
 * b、重写onReceive()方法
 * c、注册广播
 * <p/>
 * 2、BroadcastReceiver的生命周期
 * <p/>
 * 调用广播开始执行完onReceive（）结束，注意！！！如果在onReceive()
 * 执行时间过长(10s)，系统会报出应用未响应（ANR）错误！解决方案
 * <p/>
 * 3、注册广播方法
 * <p/>
 * a、静态注册：AndroidManifest.xml
 * b、动态注册：Java代码注册
 * <p/>
 * 4、广播类型
 * <p/>
 * a、普通广播：所有监听某个广播的接收者，都能收到广播
 * b、有序广播：按接收者优先顺序接收，优先级在intent-filter中priority属性设置(-1000 ~ 1000)
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

    private static final String CALLACTION = Intent.ACTION_NEW_OUTGOING_CALL;

    @Override
    public void onReceive(Context context, Intent intent) {
//        String strMsg = intent.getExtras().getString("msg");
//        Toast.makeText(context,"接收到得广播消息是："+strMsg,Toast.LENGTH_SHORT).show();

        if (intent.getAction().equals(CALLACTION)) {
            //调出拨号界面
            String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            Log.i("broadcast", "有人在再打电话:" + phoneNumber);
        } else {
            //监控电话状态
            TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Service.TELEPHONY_SERVICE);
            telephonyManager.listen(phoneStateListener,PhoneStateListener.LISTEN_CALL_STATE);
        }
    }

    PhoneStateListener phoneStateListener = new PhoneStateListener(){
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            switch (state){
//                case TelephonyManager.CALL_STATE_IDLE:
//                    Log.i("broadcast","挂断");
//                    break;
//
//                case TelephonyManager.CALL_STATE_OFFHOOK:
//                    Log.i("broadcast","接听");
//                    break;

                case TelephonyManager.CALL_STATE_RINGING:
                    Log.i("broadcast","有电话呼入,来电号码:"+incomingNumber);
                    break;
            }
        }
    };
}
