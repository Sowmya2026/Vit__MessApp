package com.example.vitmessapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Logn extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button signbutton;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private SharedPreferences sharedPreferences;
    TextView signupRedirectText;
    private static final String PREFS_NAME = "LoginPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logn);

        mAuth = FirebaseAuth.getInstance();
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        signbutton = findViewById(R.id.signbutton);
        progressBar = findViewById(R.id.progressBar);
        signupRedirectText = findViewById(R.id.signupdirect);

        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Signup.class);
                startActivity(intent);
                finish();
            }
        });

        signbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email,password;
                email=String.valueOf(editTextEmail.getText());
                password=String.valueOf(editTextPassword.getText());


                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Logn.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;

                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Logn.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;

                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Welcome back", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    Toast.makeText(Logn.this, "SignUp If you dont have Account.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });


    }


}
