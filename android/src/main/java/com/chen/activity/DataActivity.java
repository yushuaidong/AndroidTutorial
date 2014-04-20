package com.chen.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.chen.androidtutorial.R;


public class DataActivity extends Activity {

    private Button btnReturnActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        btnReturnActivity = (Button) findViewById(R.id.btnReturnActivity);
        btnReturnActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strGetMsg = getIntent().getStringExtra("joke");
                if (strGetMsg.equals("joke")){
                    Intent intentData = new Intent();
                    intentData.putExtra("ma","马云马航马伊琍,失意失联失文章");
                    setResult(RESULT_OK,intentData);
                    finish();

                } else {
                    Intent intentData = new Intent();
                    setResult(RESULT_CANCELED,intentData);
                    finish();
                }
            }
        });
    }
}
