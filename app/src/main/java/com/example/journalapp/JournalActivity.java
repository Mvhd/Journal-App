package com.example.journalapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class JournalActivity extends AppCompatActivity {

    DatabaseHelper helper;
    FloatingActionButton fabSave;
    EditText et_textArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
        helper = new DatabaseHelper(this);

        fabSave = (FloatingActionButton) findViewById(R.id.floatingActionButtonSave);
        et_textArea = (EditText) findViewById(R.id.textArea);
        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(et_textArea.getText().toString().isEmpty()){
                   Toast.makeText(JournalActivity.this,"You can't save empty page",Toast.LENGTH_LONG).show();
               }else {

                   boolean updateToDb = helper.updateToDataBase(null,null,null,null,et_textArea.getText().toString());

                   if (updateToDb) {

                       Toast.makeText(JournalActivity.this, " Saved Successfully", Toast.LENGTH_LONG).show();

                   }
               }
               // startActivity(new Intent(ViewActivity.this,JournalActivity.class));
            }
        });
    }
}
