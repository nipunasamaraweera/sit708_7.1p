package com.example.lostfound;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// Class definition for ViewItem extending AppCompatActivity
public class ViewItem extends AppCompatActivity {

    // Declare TextView variables
    TextView name, description, phone, location, date, lostFound;

    // Declare DBHelper and SQLiteDatabase variables
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    // onCreate method called when activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge display using EdgeToEdge library (if applicable)
        EdgeToEdge.enable(this);

        // Set the layout for the activity
        setContentView(R.layout.activity_view_item);

        // Initialize DBHelper and SQLiteDatabase instances
        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();

        // Initialize TextView variables by finding corresponding views in the layout
        name = findViewById(R.id.textViewName);
        description = findViewById(R.id.textViewDescription);
        phone = findViewById(R.id.textViewPhone);
        location = findViewById(R.id.textViewLocation);
        date = findViewById(R.id.textViewDate);
        lostFound = findViewById(R.id.textViewLostFound);

        // Retrieve data passed from the previous activity via Intent
        Intent i = getIntent();
        String[] arr = i.getStringArrayExtra("data");

        // Set TextViews with data retrieved from the Intent extras
        name.setText("Name: " + arr[1]);
        description.setText("Description: " + arr[2]);
        phone.setText("Phone: " + arr[3]);
        location.setText("Location: " + arr[4]);
        date.setText("Date: " + arr[5]);
        lostFound.setText("Type: " + arr[6].toUpperCase());

        // Set OnClickListener for the remove button
        findViewById(R.id.btn_remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete the corresponding item from the database based on its ID
                database.delete("my_table", "id = ?", new String[]{String.valueOf(arr[0])});

                // Display a toast message to indicate successful deletion
                Toast.makeText(ViewItem.this, "Item Deleted Successfully", Toast.LENGTH_SHORT).show();

                // Finish the current activity to return to the previous activity
                finish();
            }
        });
    }
}
