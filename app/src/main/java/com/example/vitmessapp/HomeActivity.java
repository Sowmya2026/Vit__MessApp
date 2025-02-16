package com.example.vitmessapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DrawerLayout drawerLayout;
    private CircleImageView profileImageView;

    private Button menuButton;
    private Button alarmButton;
    TextView timeTextView, veg, nonveg, spcl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        menuButton = findViewById(R.id.menubutton);
        alarmButton = findViewById(R.id.alarmbutton);

        veg = findViewById(R.id.veg);
        nonveg = findViewById(R.id.nonveg);
        spcl = findViewById(R.id.spcl);

        //  navigation drawer layout

        veg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, NonvegActivity.class));
            }
        });

        nonveg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, NonvegActivity.class));
            }
        });

        spcl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, NonvegActivity.class));
            }
        });

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MondayMenuSelection.class);
                startActivity(intent);
            }
        });

        alarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,notificationtimings.class);
                startActivity(intent);
            }
        });

        profileImageView = findViewById(R.id.profileImageView);

        // Retrieve the current user from Firebase Authentication
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("ProfileImage");
            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // Check if the user has a profile image URL
                        if (dataSnapshot.hasChild("profileImageUrl")) {
                            String profileImageUrl = dataSnapshot.child("profileImageUrl").getValue(String.class);
                            // Load profile image into ImageView using Glide library
                            Glide.with(HomeActivity.this)
                                    .load(profileImageUrl)
                                    .placeholder(R.drawable.profile_image)
                                    .into(profileImageView);
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Handle database error
                }
            });
        }

        // Navigation Drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            switch (id) {
                case R.id.nav_logout:
                    logout();
                    break;
                case R.id.nav_profile:
                    startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                    break;
                case R.id.nav_home:
                    startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                    break;
                case R.id.nav_signup:
                    startActivity(new Intent(HomeActivity.this, Signup.class));
                    break;
                case R.id.nav_login:
                    startActivity(new Intent(HomeActivity.this, Logn.class));
                    break;
                case R.id.nav_alarm:
                    startActivity(new Intent(HomeActivity.this, AlarmActivity.class));
                    break;
                case R.id.nav_selection:
                    startActivity(new Intent(HomeActivity.this, MondayMenuSelection.class));
                    break;
                case R.id.nav_feedback:
                    startActivity(new Intent(HomeActivity.this, FeedbackActivity.class));
                    break;
                case R.id.nav_contact:
                    startActivity(new Intent(HomeActivity.this, ContactusActivity.class));
                    break;

            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        findViewById(R.id.drawer_layout).setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
    }

    private void logout() {
        mAuth.signOut();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().clear().apply();

        startActivity(new Intent(HomeActivity.this, Logn.class));
        finish();
    }
}