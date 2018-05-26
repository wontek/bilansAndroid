package com.lern.baby.seti.bilans;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {

    private static final String TAG = "StartActivity";
    private static final long splashTimeOut = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

      startMainActivity();
    }

    private void startMainActivity() {

        new Handler().postDelayed( new Runnable() {


            @Override
            public void run() {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);

                finish();
            }
        }, splashTimeOut);
    }




}
