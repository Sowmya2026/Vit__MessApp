package com.example.vitmessapp;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Tuesdaymenu extends AppCompatActivity {

    EditText editTextBreakfast, editTextLunch, editTextSnacks, editTextDinner;
    Button addBreakfastButton, addLunchButton, addSnacksButton, addDinnerButton;

    // Firebase Database
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tuesday_menu);

        // Initialize Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference();
        editTextBreakfast = findViewById(R.id.Blist);
        editTextLunch = findViewById(R.id.Llist);
        editTextSnacks = findViewById(R.id.Slist);
        editTextDinner = findViewById(R.id.Dlist);
        addBreakfastButton = findViewById(R.id.addmenu);
        addLunchButton = findViewById(R.id.addmenu1);
        addSnacksButton = findViewById(R.id.addmenu2);
        addDinnerButton = findViewById(R.id.addmenu3);

        addBreakfastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMenuItem("TuesdayMenu", "Breakfast", editTextBreakfast.getText().toString());
            }
        });

        addLunchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMenuItem("TuesdayMenu", "Lunch", editTextLunch.getText().toString());
            }
        });

        addSnacksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMenuItem("TuesdayMenu", "Snacks", editTextSnacks.getText().toString());
            }
        });

        addDinnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMenuItem("TuesdayMenu", "Dinner", editTextDinner.getText().toString());
            }
        });
    }

    private void addMenuItem(String menuDay, String section, String item) {
        // Check if the item is empty
        if (item.isEmpty()) {
            Toast.makeText(this, "Please enter an item", Toast.LENGTH_SHORT).show();
            return;
        }

        // Construct the path to the specified section
        String sectionPath = menuDay + "/" + section;

        // Get the count of existing items under the specified section
        mDatabase.child(sectionPath).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Get the count of existing items
                long itemCount = task.getResult().getChildrenCount();

                // Check if the count exceeds 20
                if (itemCount >= 20) {
                    Toast.makeText(this, "You have added the maximum number of items", Toast.LENGTH_SHORT).show();
                    return; // Exit method
                }

                // Construct the path to the new item under the specified section with the next ID
                String itemId = String.valueOf(itemCount + 1);
                String itemPath = sectionPath + "/" + itemId;

                // Set the value of the new item
                mDatabase.child(itemPath).setValue(item).addOnCompleteListener(addTask -> {
                    if (addTask.isSuccessful()) {
                        Toast.makeText(this, "Item added successfully", Toast.LENGTH_SHORT).show();
                        // Clear the EditText field after adding the item
                        switch (section) {
                            case "Breakfast":
                                editTextBreakfast.setText("");
                                break;
                            case "Lunch":
                                editTextLunch.setText("");
                                break;
                            case "Snacks":
                                editTextSnacks.setText("");
                                break;
                            case "Dinner":
                                editTextDinner.setText("");
                                break;
                        }
                    } else {
                        Toast.makeText(this, "Failed to add item", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(this, "Failed to retrieve item count", Toast.LENGTH_SHORT).show();
            }
        });
    }
}