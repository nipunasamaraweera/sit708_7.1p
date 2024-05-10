package com.example.lostfound;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.database.Cursor;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

// Class definition for ListAdvertisement extending AppCompatActivity
public class ListAdvertisement extends AppCompatActivity {

    // Declare RecyclerView, Adapter, data list, DBHelper, and SQLiteDatabase variables
    private RecyclerView rv;
    private Item_Adapter adapter;
    private List<String[]> dataList;
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    // onCreate method called when the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge display using EdgeToEdge library (if applicable)
        EdgeToEdge.enable(this);

        // Set the layout for the activity
        setContentView(R.layout.activity_list_advert);

        // Initialize DBHelper and SQLiteDatabase instances
        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();

        // Initialize RecyclerView and set its layout manager
        rv = findViewById(R.id.rv_list_advert);
        rv.setLayoutManager(new LinearLayoutManager(this));

        // Initialize data list and adapter for RecyclerView
        dataList = new ArrayList<>();
        adapter = new Item_Adapter(this, dataList);

        // Call method to fetch and display data sorted by name in descending order
        getAllDataSortedByNameDesc();
    }

    // Method to fetch data from database and populate dataList sorted by name in descending order
    public void getAllDataSortedByNameDesc() {
        // Clear dataList to avoid duplicate entries
        dataList.clear();

        // Query database to retrieve data sorted by date in descending order
        Cursor cursor = database.query(
                "my_table",     // Table name
                null,           // Columns (null selects all columns)
                null,           // Selection criteria (null selects all rows)
                null,           // Selection arguments (null means no arguments)
                null,           // Group by (null means no grouping)
                null,           // Having (null means no condition)
                "date DESC"     // Order by date in descending order
        );

        // Process the cursor if it contains data
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Retrieve column values from the cursor
                @SuppressLint("Range") String id = String.valueOf(cursor.getLong(cursor.getColumnIndex("id")));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("description"));
                @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex("date"));
                @SuppressLint("Range") String phone = cursor.getString(cursor.getColumnIndex("phone"));
                @SuppressLint("Range") String location = cursor.getString(cursor.getColumnIndex("location"));
                @SuppressLint("Range") String type = cursor.getString(cursor.getColumnIndex("type"));

                // Create a String array and add it to dataList
                dataList.add(new String[]{id, name, description, phone, location, date, type});
            } while (cursor.moveToNext()); // Move to the next row in the cursor
            cursor.close(); // Close the cursor to release resources
        }

        // Set the adapter for RecyclerView to display the fetched data
        rv.setAdapter(adapter);
    }
}