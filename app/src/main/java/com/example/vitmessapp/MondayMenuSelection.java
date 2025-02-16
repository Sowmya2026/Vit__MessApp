package com.example.vitmessapp;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MondayMenuSelection extends AppCompatActivity {

    private static final String TAG = "MondayMenuSelection";

    private RecyclerView recyclerViewBreakfast, recyclerViewLunch, recyclerViewSnacks, recyclerViewDinner;
    private DatabaseReference mDatabase;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monday_menu_selection);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            userId = currentUser.getUid();
        } else {
            Log.e(TAG, "Current user is null.");
            return;
        }

        recyclerViewBreakfast = findViewById(R.id.recyclerViewBreakfast);
        recyclerViewLunch = findViewById(R.id.recyclerViewLunch);
        recyclerViewSnacks = findViewById(R.id.recyclerViewSnacks);
        recyclerViewDinner = findViewById(R.id.recyclerViewDinner);

        // Set up RecyclerView adapters
        setUpRecyclerViewAdapter("MondayMenu/Breakfast", recyclerViewBreakfast);
        setUpRecyclerViewAdapter("MondayMenu/Lunch", recyclerViewLunch);
        setUpRecyclerViewAdapter("MondayMenu/Snacks", recyclerViewSnacks);
        setUpRecyclerViewAdapter("MondayMenu/Dinner", recyclerViewDinner);

        // Initialize buttons
        Button buttonBreakfast = findViewById(R.id.buttonBreakfast);
        Button buttonLunch = findViewById(R.id.buttonLunch);
        Button buttonSnacks = findViewById(R.id.buttonSnacks);
        Button buttonDinner = findViewById(R.id.buttonDinner);

        // Set onClickListeners for buttons
        buttonBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSelectedItemsToFirebase("MondayMenu/Breakfast");
            }
        });

        buttonLunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSelectedItemsToFirebase("MondayMenu/Lunch");
            }
        });

        buttonSnacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSelectedItemsToFirebase("MondayMenu/Snacks");
            }
        });

        buttonDinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSelectedItemsToFirebase("MondayMenu/Dinner");
            }
        });
    }

    private void setUpRecyclerViewAdapter(String path, final RecyclerView recyclerView) {
        mDatabase.child(path).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> items = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String item = snapshot.getValue(String.class);
                    items.add(item);
                }
                // Set up RecyclerView adapter
                RecyclerViewAdapter adapter = new RecyclerViewAdapter(items);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(MondayMenuSelection.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
                Log.e(TAG, "Failed to retrieve data from Firebase: " + databaseError.getMessage());
            }
        });
    }

    private void saveSelectedItemsToFirebase(String path) {
        RecyclerViewAdapter adapter;
        switch (path) {
            case "MondayMenu/Breakfast":
                adapter = (RecyclerViewAdapter) recyclerViewBreakfast.getAdapter();
                break;
            case "MondayMenu/Lunch":
                adapter = (RecyclerViewAdapter) recyclerViewLunch.getAdapter();
                break;
            case "MondayMenu/Snacks":
                adapter = (RecyclerViewAdapter) recyclerViewSnacks.getAdapter();
                break;
            case "MondayMenu/Dinner":
                adapter = (RecyclerViewAdapter) recyclerViewDinner.getAdapter();
                break;
            default:
                adapter = null;
        }
        if (adapter != null) {
            // Get the list of selected items from the adapter
            List<String> selectedItems = adapter.getSelectedItemsList();

            // Construct a Map to store the selected items
            Map<String, String> selectedItemsMap = new HashMap<>();
            for (int i = 0; i < selectedItems.size(); i++) {
                // Use the item text as both key and value
                selectedItemsMap.put(String.valueOf(i + 1), selectedItems.get(i));
            }
            DatabaseReference userRef = mDatabase.child("Users").child(userId).child(path).child("selectedItems");
            userRef.setValue(selectedItemsMap)
                    .addOnSuccessListener(aVoid -> Toast.makeText(MondayMenuSelection.this, "Items saved successfully", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e -> {
                        Toast.makeText(MondayMenuSelection.this, "Failed to save items: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "Failed to save items to Firebase: " + e.getMessage());
                    });
        } else {
            Log.e(TAG, "RecyclerView adapter is null for path: " + path);
        }
    }

}

