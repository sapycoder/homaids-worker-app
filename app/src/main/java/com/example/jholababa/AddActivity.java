package com.example.jholababa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class AddActivity extends AppCompatActivity {

    EditText mName,mJob,mPhone;
    Button mAddBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        mName = findViewById(R.id.add_name);
        mJob = findViewById(R.id.add_job);
        mPhone = findViewById(R.id.add_phone);
        mAddBtn = findViewById(R.id.add_btn);

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NyDatabaseHelper myDB = new NyDatabaseHelper(AddActivity.this);
                myDB.addPerson(
                        mName.getText().toString().trim(),
                        mJob.getText().toString().trim(),
                        Integer.parseInt(mPhone.getText().toString().trim()));
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}