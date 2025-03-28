package com.example.sharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail;
    private Button btnSave;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Views
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        btnSave = findViewById(R.id.btnSave);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);

        // Load Saved Data
        loadSavedData();

        // Save Data on Button Click
        btnSave.setOnClickListener(v -> saveData());
    }

    private void saveData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", editTextName.getText().toString());
        editor.putString("email", editTextEmail.getText().toString());
        editor.apply(); // Save Data Asynchronously
    }

    private void loadSavedData() {
        String savedName = sharedPreferences.getString("name", "");
        String savedEmail = sharedPreferences.getString("email", "");
        editTextName.setText(savedName);
        editTextEmail.setText(savedEmail);
    }
}
