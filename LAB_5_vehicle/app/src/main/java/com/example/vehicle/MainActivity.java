package com.example.vehicle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner vehicleTypeSpinner;
    private EditText vehicleNumberEditText, rcNumberEditText;
    private Button submitButton;
    private String selectedVehicleType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vehicleTypeSpinner = findViewById(R.id.vehicleTypeSpinner);
        vehicleNumberEditText = findViewById(R.id.vehicleNumber);
        rcNumberEditText = findViewById(R.id.rcNumber);
        submitButton = findViewById(R.id.submitButton);

        // Spinner Data
        String[] vehicleTypes = {"Car", "Bike", "Truck", "Bus"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, vehicleTypes);
        vehicleTypeSpinner.setAdapter(adapter);

        vehicleTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedVehicleType = vehicleTypes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedVehicleType = "Car";
            }
        });

        // Submit Button Click
        submitButton.setOnClickListener(v -> {
            String vehicleNumber = vehicleNumberEditText.getText().toString().trim();
            String rcNumber = rcNumberEditText.getText().toString().trim();

            if (vehicleNumber.isEmpty() || rcNumber.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                // Sending data to ConfirmationActivity
                Intent intent = new Intent(MainActivity.this, ConfirmationActivity.class);
                intent.putExtra("vehicleType", selectedVehicleType);
                intent.putExtra("vehicleNumber", vehicleNumber);
                intent.putExtra("rcNumber", rcNumber);
                startActivity(intent);
            }
        });
    }
}
