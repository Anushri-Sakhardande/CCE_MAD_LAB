package com.example.sport;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // List of sports
    String[] sports = {
            "Cricket", "Football", "Basketball", "Tennis", "Hockey",
            "Badminton", "Volleyball", "Table Tennis", "Golf", "Baseball"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get reference to ListView
        ListView listView = findViewById(R.id.listView);
        TextView textView = findViewById(R.id.selected);

        // Create an ArrayAdapter to populate the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, sports
        );
        listView.setAdapter(adapter);

        // Handle click events
        listView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            String selectedSport = sports[position];
            textView.setText(String.format("Selected: %s", selectedSport));
        });
    }
}