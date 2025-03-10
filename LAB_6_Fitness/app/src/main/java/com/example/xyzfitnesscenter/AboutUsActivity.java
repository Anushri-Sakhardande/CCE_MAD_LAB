package com.example.xyzfitnesscenter;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        TextView textView = findViewById(R.id.aboutText);
        textView.setText("XYZ Fitness Center is dedicated to helping you achieve your fitness goals.");
    }
}
