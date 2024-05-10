package com.example.lostfound;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// DBHelper class extending SQLiteOpenHelper to manage database creation and version management
public class DBHelper extends SQLiteOpenHelper {

    // Database name and version constants
    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;

    // Constructor for DBHelper class
    public DBHelper(Context context) {
        // Call the superclass constructor to initialize the database
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // onCreate method called when the database is created for the first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL query to create the "my_table" table with specified columns
        String CREATE_TABLE_QUERY =
                "CREATE TABLE my_table (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT, " +
                        "phone TEXT, " +
                        "description TEXT, " +
                        "location TEXT, " +
                        "type TEXT, " +
                        "date TEXT)";

        // Execute the SQL query to create the table
        db.execSQL(CREATE_TABLE_QUERY);
    }

    // onUpgrade method called when the database needs to be upgraded to a new version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the existing "my_table" table if it exists
        db.execSQL("DROP TABLE IF EXISTS my_table");
        // Create a new version of the database by calling onCreate method
        onCreate(db);
    }
}