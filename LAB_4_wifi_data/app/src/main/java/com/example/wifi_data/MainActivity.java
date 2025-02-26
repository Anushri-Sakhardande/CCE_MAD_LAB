package com.example.wifi_data;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ToggleButton toggleButton = findViewById(R.id.toggleButton);
        ImageView imageView = findViewById(R.id.imageView);
        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(this, "Mobile Data", Toast.LENGTH_SHORT).show();
                imageView.setImageResource(R.drawable.signal);
            }
            else{
                Toast.makeText(this, "Wifi", Toast.LENGTH_SHORT).show();
                imageView.setImageResource(R.drawable.wifi);
            }
        });
    }
}