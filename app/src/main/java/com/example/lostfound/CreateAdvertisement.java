package com.example.lostfound;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

// CreateAdvertisement class extending AppCompatActivity
public class CreateAdvertisement extends AppCompatActivity {

    // Declare EditText fields, Button, DBHelper, SQLiteDatabase, and RadioButtons
    EditText editName, editDesc, editPhoneNo, editDate, editLocation;
    Button button;
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    RadioButton lost, found;

    // onCreate method called when the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge display using EdgeToEdge library (if applicable)
        EdgeToEdge.enable(this);

        // Set the layout for the activity
        setContentView(R.layout.activity_create_advert);

        // Initialize DBHelper and SQLiteDatabase instances
        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();

        // Initialize EditText fields, Button, and RadioButtons
        editName = findViewById(R.id.editTextName);
        editDesc = findViewById(R.id.editTextDescription);
        editPhoneNo = findViewById(R.id.editTextPhone);
        editDate = findViewById(R.id.editTextDate);
        editLocation = findViewById(R.id.editTextLocation);
        button = findViewById(R.id.buttonSave);
        lost = findViewById(R.id.radioButtonLost);
        found = findViewById(R.id.radioButtonFound);

        // Set OnClickListener for the "Save" button to handle data insertion
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDataToDB(); // Call method to add advertisement data to database
            }
        });
    }

    // Method to add advertisement data to the database
    private void addDataToDB() {
        // Retrieve data from EditText fields
        String name = editName.getText().toString();
        String description = editDesc.getText().toString();
        String phone = editPhoneNo.getText().toString();
        String date = editDate.getText().toString();
        String location = editLocation.getText().toString();
        String type = "";

        // Determine the advertisement type based on RadioButton selection
        if (lost.isChecked()) {
            type = "lost";
        } else if (found.isChecked()) {
            type = "found";
        }

        // Validate input fields
        if (name.isEmpty() || description.isEmpty() || phone.isEmpty() || date.isEmpty() || location.isEmpty() || type.isEmpty()) {
            // Display a toast message if any field is empty
            Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
            return; // Exit the method if validation fails
        }

        // Prepare ContentValues to insert data into the database
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("description", description);
        values.put("phone", phone);
        values.put("date", date);
        values.put("type", type);
        values.put("location", location);

        // Insert data into the "my_table" table using SQLiteDatabase
        db.insert("my_table", null, values);

        // Display a toast message indicating successful advertisement creation
        Toast.makeText(this, "Advertisement created successfully", Toast.LENGTH_SHORT).show();

        // Finish the activity and return to the previous screen
        finish();
    }
}