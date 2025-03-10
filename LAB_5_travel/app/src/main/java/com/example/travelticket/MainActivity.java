package com.example.travelticket;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Spinner sourceSpinner, destinationSpinner;
    private TextView dateTextView;
    private ToggleButton tripTypeToggle;
    private Button submitButton, resetButton;
    private Calendar selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sourceSpinner = findViewById(R.id.sourceSpinner);
        destinationSpinner = findViewById(R.id.destinationSpinner);
        dateTextView = findViewById(R.id.dateTextView);
        tripTypeToggle = findViewById(R.id.tripTypeToggle);
        submitButton = findViewById(R.id.submitButton);
        resetButton = findViewById(R.id.resetButton);

        // Dropdown data
        String[] cities = {"Mumbai", "Delhi", "Bangalore", "Chennai", "Hyderabad"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cities);
        sourceSpinner.setAdapter(adapter);
        destinationSpinner.setAdapter(adapter);

        // Initialize date
        selectedDate = Calendar.getInstance();
        updateDateLabel();

        // Date picker
        dateTextView.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                    (view, year, month, dayOfMonth) -> {
                        selectedDate.set(year, month, dayOfMonth);
                        updateDateLabel();
                    },
                    selectedDate.get(Calendar.YEAR),
                    selectedDate.get(Calendar.MONTH),
                    selectedDate.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });

        // Submit Button
        submitButton.setOnClickListener(v -> {
            String source = sourceSpinner.getSelectedItem().toString();
            String destination = destinationSpinner.getSelectedItem().toString();
            String date = dateTextView.getText().toString();
            String tripType = tripTypeToggle.isChecked() ? "Round-Trip" : "One-Way";

            if (source.equals(destination)) {
                Toast.makeText(MainActivity.this, "Source and Destination must be different", Toast.LENGTH_SHORT).show();
            } else {
                // Navigate to ConfirmationActivity
                Intent intent = new Intent(MainActivity.this, ConfirmationActivity.class);
                intent.putExtra("source", source);
                intent.putExtra("destination", destination);
                intent.putExtra("date", date);
                intent.putExtra("tripType", tripType);
                startActivity(intent);
            }
        });

        // Reset Button
        resetButton.setOnClickListener(v -> {
            sourceSpinner.setSelection(0);
            destinationSpinner.setSelection(0);
            tripTypeToggle.setChecked(false);
            selectedDate = Calendar.getInstance();
            updateDateLabel();
        });
    }

    private void updateDateLabel() {
        int day = selectedDate.get(Calendar.DAY_OF_MONTH);
        int month = selectedDate.get(Calendar.MONTH) + 1;
        int year = selectedDate.get(Calendar.YEAR);
        dateTextView.setText(day + "/" + month + "/" + year);
    }
}
