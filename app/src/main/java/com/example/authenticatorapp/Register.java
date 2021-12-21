package com.example.authenticatorapp;

import androidx.annotation.NonNull;
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

import com.google.android.gms.dynamic.IFragmentWrapper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity{
    private EditText mFullname,mEmail,mPassword,mPhone;
    private Button mRegisterbtn;
    private TextView mLoginbtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mFullname=findViewById(R.id.register_fullname);
        mEmail=findViewById(R.id.register_mail);
        mPassword=findViewById(R.id.register_password);
        mPhone=findViewById(R.id.register_phone);
        mRegisterbtn=findViewById(R.id.register_button);
        mLoginbtn=findViewById(R.id.regiser_click);
        progressBar=findViewById(R.id.Progressbar_register);
        fAuth=FirebaseAuth.getInstance();
        if (fAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();

        }
        mRegisterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=mEmail.getText().toString().trim();
                String Password=mPassword.getText().toString().trim();
                if (TextUtils.isEmpty(email)){
                    mEmail.setError("email is required");
                    return;
                }
                if (TextUtils.isEmpty(Password)){
                    mPassword.setError("password is required");
                    return;
                }
                if (Password.length()<6){
                    mPassword.setError("password should be minimum 6 ");
                    return;
                }
                fAuth.createUserWithEmailAndPassword(email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Register.this, "User ceated", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        } else{
                            Toast.makeText(Register.this, "Have Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(view.GONE);
                        }
                    }
                });

            }
        });
        mLoginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
    }



}