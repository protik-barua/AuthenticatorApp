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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private EditText mEmail,mPassword;
    private Button mloginbutton;
    private TextView mcreatebutton;
    private ProgressBar progressbar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmail=findViewById(R.id.login_mail);
        mPassword=findViewById(R.id.login_password);
        mloginbutton=findViewById(R.id.login_button);
        mcreatebutton=findViewById(R.id.regiser_click);
        progressbar=findViewById(R.id.Progressbar_register);
        mloginbutton.setOnClickListener(new View.OnClickListener() {
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
                fAuth.signInWithEmailAndPassword(email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Login.this, "User ceated", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        } else{
                            Toast.makeText(Login.this, "Have Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressbar.setVisibility(view.GONE);
                        }
                    }
                })

            }
        });
        mcreatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });
    }
}