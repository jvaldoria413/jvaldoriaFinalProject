package com.mobilecomputingfinals.valdoriaappfinals;

/**
 * Created by DarkHorse on 06/10/2016.
 */
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpScreen extends Activity {

    EditText TxtPass,TxtEmail,TxtPass2,TxtFname,TxtLname,TxtUname;
    String Email,Pass,Pass2,Fname,Lname,Uname;
    Button BtnShow1,BtnShow2,BtnOk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        BtnOk=(Button) findViewById(R.id.BtnOk);
        BtnShow1 = (Button) findViewById(R.id.BtnShow1);
        BtnShow2 = (Button) findViewById(R.id.BtnShow2);
        TxtPass = (EditText) findViewById(R.id.TxtPass);
        TxtEmail = (EditText) findViewById(R.id.TxtEmail);
        TxtPass2 = (EditText) findViewById(R.id.TxtPass2);
        TxtFname=(EditText)findViewById(R.id.TxtFname);
        TxtLname=(EditText)findViewById(R.id.TxtFname);
        TxtUname=(EditText)findViewById(R.id.TxtUname);
        TxtFname.requestFocus();
        BtnShow1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //int event = motionEvent.getAction();
                final int cursor = TxtPass2.getSelectionStart();
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("Classname", "ACTION_DOWN");
                        TxtPass2.setTransformationMethod(null);
                        TxtPass2.setSelection(cursor);
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("Classname", "ACTION_UP");
                        TxtPass2.setTransformationMethod(new PasswordTransformationMethod());
                        TxtPass2.setSelection(cursor);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        TxtPass2.setSelection(cursor);
                        break;
                    default:
                        break;
                }

                return true;
            }
        });
        BtnShow2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //int event = motionEvent.getAction();
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


    public void register(View view) {
        final DBAdapter sqlDB = new DBAdapter(getApplicationContext());
        Email = TxtEmail.getText().toString().trim();
        Pass= TxtPass.getText().toString().trim();
        Pass2 = TxtPass2.getText().toString().trim();
        Fname = TxtFname.getText().toString().trim();
        Lname = TxtLname.getText().toString().trim();
        Uname = TxtUname.getText().toString().trim();

        if (!Email.isEmpty() && !Pass.isEmpty() && !Pass2.isEmpty() && !Fname.isEmpty() && !Lname.isEmpty() && !Uname.isEmpty()) {
            if (!validateEmail(Uname) && validateEmail(Email) && validatePwd(Pass)) {
                // Validating username if it already exist
                if ((sqlDB.validateuname(TxtUname.getText().toString()))||(sqlDB.validateemail(TxtEmail.getText().toString()))) {
                    if (sqlDB.validateuname(TxtUname.getText().toString())){
                        Toast.makeText(getApplicationContext(), "Username already exist.", Toast.LENGTH_LONG).show();
                        TxtUname.requestFocus();
                    }
                    if (sqlDB.validateemail(TxtEmail.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Email address already exist.", Toast.LENGTH_LONG).show();
                        TxtEmail.requestFocus();
                    }
                }else if((!sqlDB.validateuname(TxtUname.getText().toString()))&&(!sqlDB.validateemail(TxtEmail.getText().toString()))){
                    if (Pass.equals(Pass2)) {

                        Log.d(SignUpScreen.this.toString(), "Signing up...");
                        String msg=sqlDB.registerUser(Fname, Lname, Uname, Email, Pass);
                        Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(SignUpScreen.this, LogInScreen.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(), "Password did not match", Toast.LENGTH_LONG).show();
                        TxtPass.setText("");
                        TxtPass2.setText("");
                        TxtPass2.requestFocus();
                    }
                }



            } else {

                if (validateEmail(Uname)) {
                    TxtUname.setError("Invalid username format");
                    TxtUname.requestFocus();
                }
                if (!validateEmail(Email)) {
                    TxtEmail.setError("Invalid Email");
                    TxtEmail.requestFocus();
                }
                if (!validatePwd(Pass)) {
                    TxtPass.setError("Atleast 8 characters");
                    TxtPass.requestFocus();

                }
                Toast.makeText(getApplicationContext(), "User cannot be added.", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Please fill up all fields", Toast.LENGTH_LONG).show();
        }
    }



    private Boolean validateEmail(String emailAdd) {
        if (emailAdd == null || !Patterns.EMAIL_ADDRESS.matcher(emailAdd).matches()) {
            return false;
        } else {
            return true;
        }
    }

    private Boolean validatePwd(String password) {
        if (password != null && password.length() >= 8) {
            return true;
        } else {
            return false;
        }
    }
}


