package com.example.vitmessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ManagerHomeActivity extends AppCompatActivity {

    private Button mondayMenuButton, sundayMenuButton, tuesdayMenuButton, wednesdayMenuButton,
            thursdayMenuButton, fridayMenuButton, saturdayMenuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_home);

        mondayMenuButton = findViewById(R.id.mondaymenu);
        sundayMenuButton = findViewById(R.id.sundaymenu);
        tuesdayMenuButton = findViewById(R.id.tuesdaymenu);
        wednesdayMenuButton = findViewById(R.id.wednesdaymenu);
        thursdayMenuButton = findViewById(R.id.thursdaymenu);
        fridayMenuButton = findViewById(R.id.fridaymenu);
        saturdayMenuButton = findViewById(R.id.saturdaymenu);

        mondayMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerHomeActivity.this, Mondaydetails.class);
                startActivity(intent);
            }
        });

        sundayMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerHomeActivity.this, MondayMenuActivity.class);
                startActivity(intent);
            }
        });

        tuesdayMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerHomeActivity.this,Tuesdaymenu.class);
                startActivity(intent);
            }
        });

        wednesdayMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerHomeActivity.this, MondayMenuActivity.class);
                startActivity(intent);
            }
        });

        thursdayMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerHomeActivity.this, MondayMenuActivity.class);
                startActivity(intent);
            }
        });

        fridayMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerHomeActivity.this, MondayMenuActivity.class);
                startActivity(intent);
            }
        });

        saturdayMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerHomeActivity.this, MondayMenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
