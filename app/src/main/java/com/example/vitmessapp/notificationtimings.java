package com.example.vitmessapp;

import static com.example.vitmessapp.NotificationScheduler.scheduleNotification;

import android.app.AlarmManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class notificationtimings extends AppCompatActivity {

    private Switch switchBreakfast, switchLunch, switchSnacks, switchDinner;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificationtimings);

        switchBreakfast = findViewById(R.id.switchBreakfast);
        switchLunch = findViewById(R.id.switchLunch);
        switchSnacks = findViewById(R.id.switchSnacks);
        switchDinner = findViewById(R.id.switchDinner);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scheduleNotifications();
                Toast.makeText(notificationtimings.this, "Notifications scheduled successfully", Toast.LENGTH_SHORT).show();
            }
        });

        // Set switch change listeners
        switchBreakfast.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle switch change
            }
        });

        switchLunch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle switch change
            }
        });

        switchSnacks.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle switch change
            }
        });

        switchDinner.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle switch change
            }
        });
    }

    private void scheduleNotifications() {
        // Implement logic to schedule notifications based on switch clicks
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        // Calculate the time for breakfast notification
        if (switchBreakfast.isChecked()) {
            // Schedule breakfast notification from 7:30 AM to 9:00 AM
            NotificationScheduler.scheduleNotification(getApplicationContext(), "Breakfast", "Time for breakfast!", 7, 30, 9, 0);
        }
        // Calculate the time for lunch notification
        if (switchLunch.isChecked()) {
            // Schedule lunch notification from 12:00 PM to 2:00 PM
            NotificationScheduler.scheduleNotification(getApplicationContext(), "Lunch", "Time for lunch!", 12, 0, 14, 0);
        }

// Calculate the time for snacks notification
        if (switchSnacks.isChecked()) {
            // Schedule snacks notification at 5:00 PM
            NotificationScheduler.scheduleNotification(getApplicationContext(), "Snacks", "Snack time!", 15, 15, 4, 0);
        }

// Calculate the time for dinner notification
        if (switchDinner.isChecked()) {
            // Schedule dinner notification from 7:30 PM to 9:00 PM
            NotificationScheduler.scheduleNotification(getApplicationContext(), "Dinner", "Dinner is ready!", 19, 30, 21, 0);
        }

    }
}
