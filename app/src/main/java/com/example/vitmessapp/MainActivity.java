package com.example.vitmessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.Manifest;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    TextView textView;
    Button lgnn, sign, managerlogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wlc);



            auth = FirebaseAuth.getInstance();
            lgnn = (Button) findViewById(R.id.lgnn);

            lgnn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, Signup.class);
                    startActivity(intent);
                }
            });
            sign = (Button) findViewById(R.id.sign);
            sign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, Logn.class);
                    startActivity(intent);

                }
            });

            managerlogin = (Button) findViewById(R.id.managerlogin);
            managerlogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, ManagerLoginActivity.class);
                    startActivity(intent);
                }
            });


        }


    }