package com.example.vitmessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StudentActivity extends AppCompatActivity {

    private Button mondaySelectionButton, sundaySelectionButton, tuesdaySelectionButton, wednesdaySelectionButton,
            thursdaySelectionButton, fridaySelectionButton, saturdaySelectionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        // Initialize buttons
        mondaySelectionButton = findViewById(R.id.mondayselection);
        sundaySelectionButton = findViewById(R.id.sundayselection);
        tuesdaySelectionButton = findViewById(R.id.tuesdayselection);
        wednesdaySelectionButton = findViewById(R.id.wednesdayselection);
        thursdaySelectionButton = findViewById(R.id.thursdayselection);
        fridaySelectionButton = findViewById(R.id.fridayselection);
        saturdaySelectionButton = findViewById(R.id.saturdayselection);

        // Set onClick listeners for each button
        mondaySelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Monday Menu Selection Activity
                Intent intent = new Intent(StudentActivity.this, MondayMenuSelection.class);
                startActivity(intent);
            }
        });

        sundaySelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Sunday Menu Selection Activity
                Intent intent = new Intent(StudentActivity.this, MondayMenuSelection.class);
                startActivity(intent);
            }
        });

        tuesdaySelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Tuesday Menu Selection Activity
                Intent intent = new Intent(StudentActivity.this, MondayMenuSelection.class);
                startActivity(intent);
            }
        });

        wednesdaySelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Wednesday Menu Selection Activity
                Intent intent = new Intent(StudentActivity.this, MondayMenuSelection.class);
                startActivity(intent);
            }
        });

        thursdaySelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Thursday Menu Selection Activity
                Intent intent = new Intent(StudentActivity.this, MondayMenuSelection.class);
                startActivity(intent);
            }
        });

        fridaySelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Friday Menu Selection Activity
                Intent intent = new Intent(StudentActivity.this, MondayMenuSelection.class);
                startActivity(intent);
            }
        });

        saturdaySelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Saturday Menu Selection Activity
                Intent intent = new Intent(StudentActivity.this, MondayMenuSelection.class);
                startActivity(intent);
            }
        });
    }
}
