package com.example.journalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormActivity extends AppCompatActivity {

    DatabaseHelper helper;
    EditText et_name,et_email,et_addPassword,et_addpassword1;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        helper = new DatabaseHelper(this);

        et_name = (EditText) findViewById(R.id.et_form_fullname);
        et_email = (EditText) findViewById(R.id.et_form_email);
        et_addPassword = (EditText) findViewById(R.id.et_form_password);
        et_addpassword1 = (EditText) findViewById(R.id.et_form_cpassword);
        addButton = (Button) findViewById(R.id.form_button);

        addFormData();
    }

    public void addFormData() {

        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                        if (et_name.getText().toString().isEmpty() && et_email.getText().toString().isEmpty()) {

                            Toast.makeText(FormActivity.this, "No empty fields allowed", Toast.LENGTH_LONG).show();

                        } else {

                        if (et_addPassword.getText().toString().equals(et_addpassword1.getText().toString())) {

                            boolean postToDb = helper.postToDataBase(et_name.getText().toString(), et_email.getText().toString(), et_addPassword.getText().toString(),null);

                            if (postToDb) {

                                Toast.makeText(FormActivity.this, "Data Inserted Successfully", Toast.LENGTH_LONG).show();

                                Intent logPage = new Intent(FormActivity.this, LoginActivity.class);
                                startActivity(logPage);

                            } else {

                                Toast.makeText(FormActivity.this, "Error: Data Not Inserted", Toast.LENGTH_LONG).show();
                            }

                        } else {

                            Toast.makeText(FormActivity.this, "Password Mismatch", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        );
    }
}

