package com.mobilecomputingfinals.valdoriaappfinals;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    Button BtnOnTouch,BtnOnTouchXY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BtnOnTouch=(Button) findViewById(R.id.BtnOnTouch);
        BtnOnTouchXY=(Button)findViewById(R.id.BtnOnTouchXY);

    }

    public void onTouch(View view) {

        Intent intent = new Intent(MainActivity.this, OnTouch.class);
        startActivity(intent);

    }

    public void onTouchXY(View view) {
        Intent intent = new Intent(MainActivity.this, OnTouchXY.class);
        startActivity(intent);


    }
}


