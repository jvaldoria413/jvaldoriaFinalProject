package com.mobilecomputingfinals.valdoriaappfinals;

/**
 * Created by DarkHorse on 06/10/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LogInScreen extends Activity {

    EditText TxtPass,TxtEmail;

    Button BtnShow,BtnLogIn;
    String uname,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen);


        BtnShow = (Button) findViewById(R.id.BtnShow);
        TxtPass = (EditText) findViewById(R.id.TxtPass2);
        TxtEmail=(EditText) findViewById(R.id.TxtEmail);
        BtnLogIn=(Button) findViewById(R.id.BtnLogIn);

        BtnShow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int cursor=TxtPass.getSelectionStart();
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("Classname","ACTION_DOWN");
                        TxtPass.setTransformationMethod(null);
                        TxtPass.setSelection(cursor);
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("Classname","ACTION_UP");
                        TxtPass.setTransformationMethod(new PasswordTransformationMethod());
                        TxtPass.setSelection(cursor);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        TxtPass.setSelection(cursor);
                        break;
                    default:
                        break;
                }

                return true;
            }
        });
    }


    public void login(View view) {
        final DBAdapter sqlDB = new DBAdapter(getApplicationContext());


        if (!validatePwd(TxtPass.getText().toString())) {
            TxtPass.setError("Invalid Password");
            TxtPass.requestFocus();
        }

        if (sqlDB.validateUser(TxtEmail.getText().toString(),TxtPass.getText().toString())) {
            Intent intent = new Intent(LogInScreen.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else{
            Toast.makeText(getApplicationContext(), "Invalid email or password", Toast.LENGTH_SHORT).show();
        }


    }



    private Boolean validatePwd(String password) {
        if (password != null && password.length() >= 8) {
            return true;
        } else {
            return false;
        }
    }


    public void SignUp(View v) {
        Intent intent=new Intent(this,SignUpScreen.class);
        startActivity(intent);
    }



}

