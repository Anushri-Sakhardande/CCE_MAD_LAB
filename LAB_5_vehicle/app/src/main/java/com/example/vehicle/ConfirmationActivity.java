package com.example.vehicle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.UUID;

public class ConfirmationActivity extends AppCompatActivity {

    private TextView detailsTextView;
    private Button confirmButton, editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        detailsTextView = findViewById(R.id.detailsTextView);
        confirmButton = findViewById(R.id.confirmButton);
        editButton = findViewById(R.id.editButton);

        // Receiving data from MainActivity
        Intent intent = getIntent();
        String vehicleType = intent.getStringExtra("vehicleType");
        String vehicleNumber = intent.getStringExtra("vehicleNumber");
        String rcNumber = intent.getStringExtra("rcNumber");

        String details = "Vehicle Type: " + vehicleType + "\nVehicle Number: " + vehicleNumber + "\nRC Number: " + rcNumber;
        detailsTextView.setText(details);

        // Confirm Button Click
        confirmButton.setOnClickListener(v -> {
            String serialNumber = UUID.randomUUID().toString().substring(0, 8);
            Toast.makeText(ConfirmationActivity.this, "Parking Allotted! Serial No: " + serialNumber, Toast.LENGTH_LONG).show();
            finish();
        });

        // Edit Button Click
        editButton.setOnClickListener(v -> finish());
    }
}

