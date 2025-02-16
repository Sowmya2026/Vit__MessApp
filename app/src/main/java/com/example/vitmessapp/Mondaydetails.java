package com.example.vitmessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Mondaydetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mondaydetails);

        Button addButton = findViewById(R.id.button2);
        Button percentageButton = findViewById(R.id.button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mondaydetails.this,MondayMenuActivity.class);
                startActivity(intent);
            }
        });

        percentageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mondaydetails.this, percentage.class);
                startActivity(intent);
            }
        });
    }
}
