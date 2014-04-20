package com.chen.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chen.androidtutorial.R;
import com.chen.barcode.IntentIntegrator;
import com.chen.barcode.IntentResult;

public class ScanBarcodeActivity extends Activity {

    private Button btnScanBarcode;
    private TextView tvScanBarcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_barcode);

        tvScanBarcode = (TextView)findViewById(R.id.tvScanBarcode);
        btnScanBarcode = (Button) findViewById(R.id.btnScanBarcode);
        btnScanBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //调用Barcode Scanner扫描条码
                IntentIntegrator intentIntegrator = new IntentIntegrator(ScanBarcodeActivity.this);
                intentIntegrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //处理扫描结果
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(scanResult != null){
            //扫描成功
            tvScanBarcode.setText(scanResult.toString());
        } else {
            //扫描失败

        }
    }
}
