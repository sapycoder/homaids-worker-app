package com.example.jholababa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText mEmail,mPassword;
    Button mButton;
    TextView mRegisterbtn;
    ProgressBar mPB;
    FirebaseAuth fbAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mButton = findViewById(R.id.btn);
        mRegisterbtn = findViewById(R.id.bottomtext);
        mPB = findViewById(R.id.pb);
        fbAuth = FirebaseAuth.getInstance();

        mButton.setOnClickListener(v -> {
            String email = mEmail.getText().toString().trim();
            String password = mPassword.getText().toString().trim();

            if(TextUtils.isEmpty(email)){
                mEmail.setError("Email is required.");
                return;
            }
            if(TextUtils.isEmpty(password)){
                mPassword.setError("Password is required.");
                return;
            }
            if(password.length() < 5){
                mPassword.setError("Password must be atleast than 5 characters");
                return;
            }

            mPB.setVisibility(View.VISIBLE);

            //REGISTERING THE USER TO FIREBASE
            fbAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    Toast.makeText(Login.this,"Logged In Successfully",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
                else{
                    Toast.makeText(Login.this, "Error! Invalid Email or Password.", Toast.LENGTH_LONG).show();
                    //+ task.getException().getMessage()
                    startActivity(new Intent(getApplicationContext(),Login.class));
                }
                mPB.setVisibility(View.GONE);
            });
        });
        mRegisterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });

    }
}