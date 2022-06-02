package com.example.sharedpreferencestest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button saveData = findViewById(R.id.save_data);
        saveData.setOnClickListener(v -> {
            SharedPreferences.Editor editor
                    = getSharedPreferences("data", MODE_PRIVATE).edit();
            editor.putString("name", "Tom");
            editor.putInt("age", 28);
            editor.putBoolean("married",false);
            editor.apply();
        });

        Button restoreData = findViewById(R.id.restore_data);
        restoreData.setOnClickListener(v -> {
            SharedPreferences preferences = getSharedPreferences("data", MODE_PRIVATE);
            Log.d(TAG, "name is " + preferences.getString("name", ""));
            Log.d(TAG, "age is " + preferences.getInt("age", 0));
            Log.d(TAG, "married is " + preferences.getBoolean("married", false));
        });
    }
}