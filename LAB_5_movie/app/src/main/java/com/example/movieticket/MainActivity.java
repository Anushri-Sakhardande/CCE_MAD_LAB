package com.example.movieticket;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Spinner movieSpinner, theatreSpinner;
    private TextView dateTextView, timeTextView;
    private ToggleButton ticketTypeToggle;
    private Button bookNowButton, resetButton;
    private Calendar selectedDate, selectedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieSpinner = findViewById(R.id.movieSpinner);
        theatreSpinner = findViewById(R.id.theatreSpinner);
        dateTextView = findViewById(R.id.dateTextView);
        timeTextView = findViewById(R.id.timeTextView);
        ticketTypeToggle = findViewById(R.id.ticketTypeToggle);
        bookNowButton = findViewById(R.id.bookNowButton);
        resetButton = findViewById(R.id.resetButton);

        // Populate movie & theatre spinners
        String[] movies = {"Dune 2", "Oppenheimer", "Jawan", "Avatar 2"};
        String[] theatres = {"PVR Cinemas", "INOX", "Carnival", "IMAX"};

        ArrayAdapter<String> movieAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, movies);
        ArrayAdapter<String> theatreAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, theatres);

        movieSpinner.setAdapter(movieAdapter);
        theatreSpinner.setAdapter(theatreAdapter);

        // Initialize date & time
        selectedDate = Calendar.getInstance();
        selectedTime = Calendar.getInstance();
        updateDateLabel();
        updateTimeLabel();

        // Date Picker
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

        // Time Picker
        timeTextView.setOnClickListener(v -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                    (view, hourOfDay, minute) -> {
                        selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        selectedTime.set(Calendar.MINUTE, minute);
                        updateTimeLabel();
                        validatePremiumBooking(hourOfDay);
                    },
                    selectedTime.get(Calendar.HOUR_OF_DAY),
                    selectedTime.get(Calendar.MINUTE), false);
            timePickerDialog.show();
        });

        // Toggle Button Logic
        ticketTypeToggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            validatePremiumBooking(selectedTime.get(Calendar.HOUR_OF_DAY));
        });

        // Book Now Button
        bookNowButton.setOnClickListener(v -> {
            String movie = movieSpinner.getSelectedItem().toString();
            String theatre = theatreSpinner.getSelectedItem().toString();
            String date = dateTextView.getText().toString();
            String time = timeTextView.getText().toString();
            String ticketType = ticketTypeToggle.isChecked() ? "Premium" : "Standard";

            Intent intent = new Intent(MainActivity.this, ConfirmationActivity.class);
            intent.putExtra("movie", movie);
            intent.putExtra("theatre", theatre);
            intent.putExtra("date", date);
            intent.putExtra("time", time);
            intent.putExtra("ticketType", ticketType);
            startActivity(intent);
        });

        // Reset Button
        resetButton.setOnClickListener(v -> {
            movieSpinner.setSelection(0);
            theatreSpinner.setSelection(0);
            ticketTypeToggle.setChecked(false);
            selectedDate = Calendar.getInstance();
            selectedTime = Calendar.getInstance();
            updateDateLabel();
            updateTimeLabel();
            bookNowButton.setEnabled(true);
        });
    }

    private void updateDateLabel() {
        int day = selectedDate.get(Calendar.DAY_OF_MONTH);
        int month = selectedDate.get(Calendar.MONTH) + 1;
        int year = selectedDate.get(Calendar.YEAR);
        dateTextView.setText(day + "/" + month + "/" + year);
    }

    private void updateTimeLabel() {
        int hour = selectedTime.get(Calendar.HOUR_OF_DAY);
        int minute = selectedTime.get(Calendar.MINUTE);
        String timeFormat = hour > 12 ? (hour - 12) + ":" + minute + " PM" : hour + ":" + minute + " AM";
        timeTextView.setText(timeFormat);
    }

    private void validatePremiumBooking(int hour) {
        if (ticketTypeToggle.isChecked() && hour < 12) {
            bookNowButton.setEnabled(false);
            Toast.makeText(this, "Premium tickets can only be booked after 12:00 PM", Toast.LENGTH_SHORT).show();
        } else {
            bookNowButton.setEnabled(true);
        }
    }
}
