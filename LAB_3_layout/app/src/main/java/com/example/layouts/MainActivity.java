package com.example.layouts;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get button references
        Button listButton = findViewById(R.id.button2);
        Button tableButton = findViewById(R.id.button3);
        Button gridButton = findViewById(R.id.button4);
        Button tabButton = findViewById(R.id.button);

        // Set click listeners for each button
        listButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ListActivity.class)));
        tableButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TableActivity.class)));
        gridButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, GridActivity.class)));
        tabButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TabActivity.class)));
    }
}