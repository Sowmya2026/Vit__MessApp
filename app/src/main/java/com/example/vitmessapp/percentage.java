package com.example.vitmessapp;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vitmessapp.MenuAdapter;
import com.example.vitmessapp.MenuItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class percentage extends AppCompatActivity {

    private static final String TAG = "PercentageActivity";
    private RecyclerView recyclerViewBreakfast, recyclerViewLunch, recyclerViewSnacks, recyclerViewDinner;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percentage);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            userId = currentUser.getUid();
        } else {
            // Handle the case where the user is not logged in
            // For example, you can redirect to the login activity
            // or display a message asking the user to log in.
            return;
        }
        // Initialize RecyclerViews
        recyclerViewBreakfast = findViewById(R.id.recyclerViewBreakfast2);
        recyclerViewLunch = findViewById(R.id.recyclerViewLunch1);
        recyclerViewSnacks = findViewById(R.id.recyclerViewSnacks1);
        recyclerViewDinner = findViewById(R.id.recyclerViewDinner1);

        // Set layout manager for RecyclerViews
        recyclerViewBreakfast.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewLunch.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSnacks.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewDinner.setLayoutManager(new LinearLayoutManager(this));

        // Set up RecyclerView adapters
        setUpRecyclerViewAdapter("MondayMenu/Breakfast", recyclerViewBreakfast);
        setUpRecyclerViewAdapter("MondayMenu/Lunch", recyclerViewLunch);
        setUpRecyclerViewAdapter("MondayMenu/Snacks", recyclerViewSnacks);
        setUpRecyclerViewAdapter("MondayMenu/Dinner", recyclerViewDinner);
    }

    private void setUpRecyclerViewAdapter(String path, final RecyclerView recyclerView) {
        mDatabase.child(path).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<MenuItem> items = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Check if the data is a string
                    if (snapshot.getValue() instanceof String) {
                        // If it's a string, create a MenuItem object with the string as the name
                        String itemName = snapshot.getValue(String.class);
                        MenuItem menuItem = new MenuItem(itemName, ""); // Assuming the description is empty
                        items.add(menuItem);
                    } else {
                        // If it's not a string, assume it's a MenuItem object
                        MenuItem menuItem = snapshot.getValue(MenuItem.class);
                        items.add(menuItem);
                    }
                }
                // Set up RecyclerView adapter
                MenuAdapter adapter = new MenuAdapter(items);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(percentage.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
                Log.e(TAG, "Failed to retrieve data from Firebase: " + databaseError.getMessage());
            }
        });
    }

    // Assuming this is the method or function where you handle the user's selection of item
    // Method to calculate percentage for the selected items
    private void calculatePercentageForSelectedItems(String path, List<MenuItem> items) {
        // Calculate total number of selected items
        int totalSelectedItems = Math.min(items.size(), 10); // Limit to 10 selected items

        // Calculate total number of available items (20)
        int totalAvailableItems = 20;

        // Iterate through the items list to calculate and update the percentage
        int index = 1;
        for (MenuItem item : items) {
            // Calculate the percentage based on the index and total number of available items
            double percentage = ((double) index / totalAvailableItems) * 100.0;
            // Append the percentage to the description
            String descriptionWithPercentage = item.getDescription() + " - " + String.format("%.2f%%", percentage);
            // Update the MenuItem object with the new description
            item.setDescription(descriptionWithPercentage);
            index++;
        }

        // Now, store the modified items in Firebase
        DatabaseReference userRef = mDatabase.child("Users").child(userId).child(path).child("selectedItems");
        userRef.setValue(items);
    }
    // Method to get the current user's ID
    private String getUserId() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            return user.getUid();
        }
        return null;
    }
}
