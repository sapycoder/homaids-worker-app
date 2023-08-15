package com.example.jholababa;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText mName,mJob,mPhone;
    Button mUpdateBtn,mDeleteBtn;
    String id,name,job,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        mName = findViewById(R.id.update_name);
        mJob = findViewById(R.id.update_job);
        mPhone = findViewById(R.id.update_phone);
        mUpdateBtn = findViewById(R.id.update_btn);
        mDeleteBtn = findViewById(R.id.delete_button);

        //ALWAYS CALL THIS GET AND SET DATA
        getAndSetIntentData();

        //SET ACTIONBAR TITLE DYNAMICALLY
        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }

        //UPDATE BUTTON ACTION
        mUpdateBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //THEN CALL THIS FUNCTION
                NyDatabaseHelper myDB = new NyDatabaseHelper(UpdateActivity.this);
                name = mName.getText().toString().trim();
                job = mJob.getText().toString().trim();
                phone = mPhone.getText().toString().trim();
                myDB.updateData(id,name,job,phone);
            }
        });

        //DELETE BUTTON ACTION
        mDeleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id")
                && getIntent().hasExtra("name")
                && getIntent().hasExtra("job")
                && getIntent().hasExtra("phone"))
        {
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            job = getIntent().getStringExtra("job");
            phone = getIntent().getStringExtra("phone");

            //SETTING DATA
            mName.setText(name);
            mJob.setText(job);
            mPhone.setText(phone);
        }
        else {
            Toast.makeText(this,".No Data Inserted!",Toast.LENGTH_SHORT).show();
            }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                NyDatabaseHelper myDB = new NyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}