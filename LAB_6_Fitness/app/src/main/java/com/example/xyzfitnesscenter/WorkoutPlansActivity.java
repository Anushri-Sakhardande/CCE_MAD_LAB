package com.example.xyzfitnesscenter;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class WorkoutPlansActivity extends AppCompatActivity {

    private ListView listView;
    private String[] workoutPlans = {"Weight Loss", "Cardio", "Strength Training", "Yoga"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_plans);

        listView = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, workoutPlans);
        listView.setAdapter(adapter);
    }
}
