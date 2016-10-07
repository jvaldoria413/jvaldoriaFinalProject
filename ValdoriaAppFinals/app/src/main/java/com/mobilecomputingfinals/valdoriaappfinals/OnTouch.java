package com.mobilecomputingfinals.valdoriaappfinals;

/**
 * Created by DarkHorse on 06/10/2016.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class OnTouch extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ontouch);

        final String tag=OnTouch.class.getSimpleName();
        final ImageView imageView=(ImageView)findViewById(R.id.imageView);

        assert imageView != null;
        imageView.setOnTouchListener(new View.OnTouchListener(){
            float initX,initY,finalX,finalY;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent){
                int event = motionEvent.getAction();
                switch (event) {
                    case MotionEvent.ACTION_DOWN:
                        initX = motionEvent.getX();
                        initY = motionEvent.getY();
                        Toast.makeText(getApplicationContext(), "value of X: " + initX + " value of Y: " + initY, Toast.LENGTH_LONG).show();
                        return true;
                    case MotionEvent.ACTION_UP:
                        finalX = motionEvent.getX();
                        finalY = motionEvent.getY();

                        Log.d(tag, "Action was UP");

                        if (initX < finalX) {
                            Log.d(tag, "Swiped Left to Right");
                            Toast.makeText(getApplicationContext(), "Swiped Left to Right Swipe, value of X: " + finalX + " value of Y: " + finalY, Toast.LENGTH_LONG).show();

                        }
                        if (initX > finalX) {
                            Log.d(tag, "Swiped Right to Left");
                            Toast.makeText(getApplicationContext(), "Swiped Right to Left , value of X: " + finalX + " value of Y: " + finalY, Toast.LENGTH_LONG).show();

                        }
                        if (initY < finalY) {
                            Log.d(tag, "Swiped Up to Down");
                            Toast.makeText(getApplicationContext(), "Swiped Up to Down, value of X: " + finalX + " value of Y: " + finalY, Toast.LENGTH_LONG).show();

                        }
                        if (initY > finalY) {
                            Log.d(tag, "Swiped Down to Up");
                            Toast.makeText(getApplicationContext(), "Swiped Down to Up, value of X: " + finalX + " value of Y: " + finalY, Toast.LENGTH_LONG).show();

                        }
                        return true;

                }

                return false;
            }
        });
    }
}
