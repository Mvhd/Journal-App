package com.example.journalapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper helper;

    TextView tv_fpassword;
    TextView tv_sign_up;
    EditText et_user;
    EditText et_pass;
    Button button_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        helper = new DatabaseHelper(this);
        tv_sign_up = (TextView) findViewById(R.id.tv_sign_up);
        tv_fpassword = (TextView) findViewById(R.id.tv_fpassword);
        et_user = (EditText) findViewById(R.id.et_username);
        et_pass = (EditText) findViewById(R.id.et_password);
        button_login = (Button) findViewById(R.id.b_login);

        checkLogin();

        tv_sign_up.setOnClickListener(new signUpActivity());
        tv_fpassword.setOnClickListener(new forgotPasswordActivity());

    }

    class signUpActivity implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent signUp = new Intent(LoginActivity.this, FormActivity.class);
            startActivity(signUp);
        }
    }

    class forgotPasswordActivity implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent fpassword = new Intent(LoginActivity.this, PasswordActivity.class);
            startActivity(fpassword);
        }
    }

    public void checkLogin() {
        button_login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name= et_user.getText().toString();

                        String password= et_pass.getText().toString();

                        Cursor cursor = helper.fetchFromDataBase();

                        cursor.moveToFirst();
                        boolean isLogin = false;
                        String USERNAME = "";
                        do {
                            if(name.equals(cursor.getString(1)) || name.equals(cursor.getString(2)) && password.equals(cursor.getString(3))){
                                isLogin = true;
                                USERNAME = cursor.getString(1);
                            }
                        }
                        while (cursor.moveToNext());
                        if(isLogin){
                            Toast.makeText(LoginActivity.this,"Login Successful, "+USERNAME,Toast.LENGTH_LONG).show();
                            startActivity(new Intent(LoginActivity.this,ViewActivity.class));

                        }else{
                            Toast.makeText(LoginActivity.this,"Invalid UserName or Password",Toast.LENGTH_LONG).show();

                        }
                    }
                }
        );
    }
}
