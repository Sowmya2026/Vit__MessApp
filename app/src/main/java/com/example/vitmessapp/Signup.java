package com.example.vitmessapp;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    EditText editTextFullName, editTextEmail, editTextPassword;
    Button signupButton;
    ProgressBar progressBar;
    TextView loginRedirectText;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        editTextFullName = findViewById(R.id.fullName);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        signupButton = findViewById(R.id.signupbutton);
        progressBar = findViewById(R.id.progressBar);
        loginRedirectText = findViewById(R.id.signupnow);

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Logn.class);
                startActivity(intent);
                finish();
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                final String fullName = editTextFullName.getText().toString().trim();
                final String email = editTextEmail.getText().toString().trim();
                final String password = editTextPassword.getText().toString().trim();

                if (TextUtils.isEmpty(fullName)) {
                    Toast.makeText(Signup.this, "Enter Full Name", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Signup.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Signup.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // User registration successful
                                    String userId = mAuth.getCurrentUser().getUid();
                                    Users user = new Users(fullName, email, password);
                                    databaseReference.child(userId).setValue(user);

                                    Toast.makeText(Signup.this, "User registered successfully", Toast.LENGTH_SHORT).show();

                                    // Navigate to Login activity
                                    Intent intent = new Intent(Signup.this, Logn.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // Registration failed
                                    Toast.makeText(Signup.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
