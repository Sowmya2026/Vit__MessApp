package com.example.vitmessapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedbackActivity extends AppCompatActivity {

    private EditText editTextFeedback;
    private Button buttonSubmitFeedback;

    private DatabaseReference feedbackRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        // Initialize Firebase
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        String userId = mAuth.getCurrentUser().getUid();
        feedbackRef = mDatabase.getReference().child("Users").child(userId).child("feedback");

        // Initialize views
        editTextFeedback = findViewById(R.id.editTextFeedback);
        buttonSubmitFeedback = findViewById(R.id.buttonSubmitFeedback);

        // Set click listener for submit button
        buttonSubmitFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitFeedback();
            }
        });
    }

    private void submitFeedback() {
        // Get the feedback text from the EditText
        String feedbackText = editTextFeedback.getText().toString().trim();

        // Check if feedback text is empty
        if (feedbackText.isEmpty()) {
            Toast.makeText(this, "Please enter your feedback", Toast.LENGTH_SHORT).show();
            return;
        }

        // Push the feedback text to the Firebase database
        feedbackRef.push().setValue(feedbackText, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@NonNull DatabaseError error, @NonNull DatabaseReference ref) {
                if (error != null) {
                    // Error occurred while saving feedback
                    Toast.makeText(FeedbackActivity.this, "Failed to submit feedback: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    // Feedback submitted successfully
                    Toast.makeText(FeedbackActivity.this, "Feedback submitted successfully", Toast.LENGTH_SHORT).show();
                    // Clear the EditText after submission
                    editTextFeedback.setText("");
                }
            }
        });
    }
}
