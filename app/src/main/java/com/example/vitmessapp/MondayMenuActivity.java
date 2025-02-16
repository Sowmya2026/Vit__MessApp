package com.example.vitmessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MondayMenuActivity extends AppCompatActivity {

    EditText editTextBreakfast, editTextLunch, editTextSnacks, editTextDinner;
    Button addBreakfastButton, addLunchButton, addSnacksButton, addDinnerButton;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monday_menu);

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
                addMenuItem("MondayMenu", "Breakfast", editTextBreakfast.getText().toString());
            }
        });

        addLunchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMenuItem("MondayMenu", "Lunch", editTextLunch.getText().toString());
            }
        });

        addSnacksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMenuItem("MondayMenu", "Snacks", editTextSnacks.getText().toString());
            }
        });

        addDinnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMenuItem("MondayMenu", "Dinner", editTextDinner.getText().toString());
            }
        });
    }

    private void initializeSpinner(int spinnerId) {
        Spinner spinner = findViewById(spinnerId);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.selection_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void addMenuItem(String menuDay, String section, String item) {
        if (item.isEmpty()) {
            Toast.makeText(this, "Please enter an item", Toast.LENGTH_SHORT).show();
            return;
        }
        String sectionPath = menuDay + "/" + section;
        // it will count of existing items under specified sections
        mDatabase.child(sectionPath).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                long itemCount = task.getResult().getChildrenCount();
                if (itemCount >= 20) {
                    Toast.makeText(this, "You have added the maximum number of items", Toast.LENGTH_SHORT).show();
                    return;
                }
                String itemId = String.valueOf(itemCount + 1);
                String itemPath = sectionPath + "/" + itemId;

                mDatabase.child(itemPath).setValue(item).addOnCompleteListener(addTask -> {
                    if (addTask.isSuccessful()) {
                        Toast.makeText(this, "Item added successfully", Toast.LENGTH_SHORT).show();
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