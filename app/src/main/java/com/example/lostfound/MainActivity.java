package com.example.lostfound;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

// Class definition for MainActivity extending AppCompatActivity
public class MainActivity extends AppCompatActivity {

    // onCreate method called when the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge display using EdgeToEdge library (if applicable)
        EdgeToEdge.enable(this);

        // Set the layout for the activity
        setContentView(R.layout.activity_main);

        // Set OnClickListener for the "New Advertisement" button
        findViewById(R.id.newAd).setOnClickListener(v -> {
            // Create an Intent to navigate to CreateAdvertisement activity
            Intent i = new Intent(MainActivity.this, CreateAdvertisement.class);
            // Start the CreateAdvertisement activity
            startActivity(i);
        });

        // Set OnClickListener for the "List Advertisements" button
        findViewById(R.id.listAds).setOnClickListener(v -> {
            // Create an Intent to navigate to ListAdvertisement activity
            Intent i = new Intent(MainActivity.this, ListAdvertisemet.class);
            // Start the ListAdvertisement activity
            startActivity(i);
        });
    }
}
