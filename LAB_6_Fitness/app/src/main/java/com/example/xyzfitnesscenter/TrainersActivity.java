package com.example.xyzfitnesscenter;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class TrainersActivity extends AppCompatActivity {

    private ListView listView;
    private String[] trainers = {"John - Weight Training", "Lisa - Yoga Expert", "Rahul - Cardio Trainer"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainers);

        listView = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, trainers);
        listView.setAdapter(adapter);
    }
}
