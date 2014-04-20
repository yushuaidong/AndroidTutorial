package com.chen.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chen.androidtutorial.R;

/**
 * Created with IntelliJ IDEA.
 * Date: 3/19/14
 * Time: 10:59
 * Info:
 */
public class IndexActivity extends Activity {

    private Button btnMainActivity, btnSecondActivity, btnActivityLifeCycle, btnCaptureActivity;
    private Button btnBroadcastActivity, btnDynamicBroadcastReceiver,btnScanActivity;
    private ButtonListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //绑定布局文件
        setContentView(R.layout.activity_index);

        listener = new ButtonListener();

        FindView();

        SetClickListener();

    }

    private void FindView() {
        btnMainActivity = (Button) findViewById(R.id.btnMainActivity);
        btnSecondActivity = (Button) findViewById(R.id.btnSecondActivity);
        btnActivityLifeCycle = (Button) findViewById(R.id.btnActivityLifeCycle);
        btnCaptureActivity = (Button) findViewById(R.id.btnCaptureActivity);
        btnBroadcastActivity = (Button) findViewById(R.id.btnBroadcastActivity);
        btnDynamicBroadcastReceiver = (Button) findViewById(R.id.btnDynamicBroadcastReceiver);
        btnScanActivity = (Button) findViewById(R.id.btnScanActivity);
    }

    private void SetClickListener() {
        btnMainActivity.setOnClickListener(listener);
        btnSecondActivity.setOnClickListener(listener);
        btnActivityLifeCycle.setOnClickListener(listener);
        btnCaptureActivity.setOnClickListener(listener);
        btnBroadcastActivity.setOnClickListener(listener);
        btnDynamicBroadcastReceiver.setOnClickListener(listener);
        btnScanActivity.setOnClickListener(listener);
    }

    private class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnMainActivity:
                    //功能
                    Intent i1 = new Intent(IndexActivity.this, MainActivity.class);
                    startActivity(i1);
                    break;
                case R.id.btnSecondActivity:
                    Intent i2 = new Intent(IndexActivity.this, SecondActivity.class);
                    startActivity(i2);
                    break;
                case R.id.btnActivityLifeCycle:
                    Intent i3 = new Intent(IndexActivity.this, ActivityLifeCycle.class);
                    startActivity(i3);
                    break;
                case R.id.btnCaptureActivity:
                    Intent i4 = new Intent(IndexActivity.this, CaptureActivity.class);
                    startActivity(i4);
                    break;
                case R.id.btnBroadcastActivity:
                    Intent i5 = new Intent(IndexActivity.this, SendBroadcastActivity.class);
                    startActivity(i5);
                    break;
                case R.id.btnDynamicBroadcastReceiver:
                    Intent i6 = new Intent(IndexActivity.this, DynamicRegisterBroadcastReceiver.class);
                    startActivity(i6);
                    break;
                case R.id.btnScanActivity:
                    Intent i7 = new Intent(IndexActivity.this, ScanBarcodeActivity.class);
                    startActivity(i7);
                    break;
            }
        }
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //应用程序退出方式一：确认退出对话框
//        if (keyCode == KeyEvent.KEYCODE_BACK){
//            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
//            alertDialog.setTitle("提示");
//            alertDialog.setMessage("确认退出马上学Android");
//            alertDialog.setPositiveButton("确认",new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    finish();
//                }
//            });
//
//            alertDialog.setNegativeButton("取消",new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    return;
//                }
//            });
//
//            alertDialog.show();
//        }
        //应用程序退出方式二：连续按两次确认退出
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){

            if((System.currentTimeMillis() - exitTime) >2000){
                Toast.makeText(this,"再按一次退出应用程序",Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
