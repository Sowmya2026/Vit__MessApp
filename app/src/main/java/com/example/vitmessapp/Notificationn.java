package com.example.vitmessapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Notificationn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificationn);

        // Initialization code or logic
        displayWelcomeMessage();
    }

    private void displayWelcomeMessage() {
        // Display a welcome message using a Toast
        Toast.makeText(this, "Welcome to Notification Activity", Toast.LENGTH_SHORT).show();
    }
}
