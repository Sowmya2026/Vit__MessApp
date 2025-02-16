package com.example.vitmessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ManagerLoginActivity extends AppCompatActivity {

    EditText editTextEmail, editTextPassword;
    Button loginButton;
    ProgressBar progressBar;
    TextView forgotPasswordTextView;
    private static final String MANAGER_EMAIL = "manager2024@gmail.com";
    private static final String MANAGER_PASSWORD = "Manager2024";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_login);

        editTextEmail = findViewById(R.id.email1);
        editTextPassword = findViewById(R.id.password1);
        loginButton = findViewById(R.id.lognnnbutton);
        progressBar = findViewById(R.id.progressBar1);
        forgotPasswordTextView = findViewById(R.id.text12);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ManagerLoginActivity.this, "Forgot password clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loginUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        progressBar.setVisibility(View.VISIBLE);

        if (email.equals(MANAGER_EMAIL) && password.equals(MANAGER_PASSWORD)) {
            Toast.makeText(ManagerLoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), ManagerHomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(ManagerLoginActivity.this, "Students Cann't Login.", Toast.LENGTH_SHORT).show();
        }

        progressBar.setVisibility(View.GONE);
    }
}
