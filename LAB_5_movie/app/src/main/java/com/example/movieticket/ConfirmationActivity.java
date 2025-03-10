package com.example.movieticket;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ConfirmationActivity extends AppCompatActivity {

    private TextView confirmationDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        confirmationDetails = findViewById(R.id.confirmationDetails);

        Intent intent = getIntent();
        String movie = intent.getStringExtra("movie");
        String theatre = intent.getStringExtra("theatre");
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");
        String ticketType = intent.getStringExtra("ticketType");

        String details = "Movie: " + movie + "\nTheatre: " + theatre +
                "\nDate: " + date + "\nTime: " + time +
                "\nTicket Type: " + ticketType + "\nSeats Available: 30";
        confirmationDetails.setText(details);
    }
}
