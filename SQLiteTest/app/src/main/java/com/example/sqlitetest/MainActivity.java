package com.example.sqlitetest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 2);
        Button crateDatabase = findViewById(R.id.create_database);
        crateDatabase.setOnClickListener(v -> {
            dbHelper.getWritableDatabase();
        });
        Button addData = findViewById(R.id.add_data);
        addData.setOnClickListener(v -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", "The Da Vinci Code");
            values.put("author", "Dan Brown");
            values.put("pages", 454);
            values.put("price", 16.96);
            db.insert("Book", null, values);
            values.clear();
            values.put("name", "The Lost Symbol");
            values.put("author", "Dan Brown");
            values.put("pages", 510);
            values.put("price", 19.95);
            db.insert("Book", null, values);
        });
        Button updateData = findViewById(R.id.update_data);
        updateData.setOnClickListener(v -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("price", 10.99);
            db.update("Book",  values, "name = ?", new String[] {
                    "The Da Vinci Code"
            });
        });
        Button deleteData = findViewById(R.id.delete_data);
        deleteData.setOnClickListener(v -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.delete("Book", "pages > ?", new String[]{"500"});
        });
        Button queryData = findViewById(R.id.query_data);
        queryData.setOnClickListener(v -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            Cursor cursor = db.query("Book", null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                    @SuppressLint("Range") String author = cursor.getString(cursor.getColumnIndex("author"));
                    @SuppressLint("Range") int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                    @SuppressLint("Range") double price = cursor.getDouble(cursor.getColumnIndex("price"));
                    Log.d(TAG, "name: " + name);
                    Log.d(TAG, "author: " + author);
                    Log.d(TAG, "pages: " + pages);
                    Log.d(TAG, "price: " + price);
                } while (cursor.moveToNext());
            }
            cursor.close();
        });
    }
}