package com.example.travelticket;

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

        // Retrieve data from intent
        Intent intent = getIntent();
        String source = intent.getStringExtra("source");
        String destination = intent.getStringExtra("destination");
        String date = intent.getStringExtra("date");
        String tripType = intent.getStringExtra("tripType");

        String details = "Source: " + source + "\nDestination: " + destination +
                "\nDate: " + date + "\nTrip Type: " + tripType;
        confirmationDetails.setText(details);
    }
}
