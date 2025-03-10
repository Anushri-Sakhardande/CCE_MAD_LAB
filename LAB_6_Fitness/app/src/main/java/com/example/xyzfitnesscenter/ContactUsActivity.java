package com.example.xyzfitnesscenter;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ContactUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        TextView textView = findViewById(R.id.contactText);
        textView.setText("Contact us at: xyzfitness@gmail.com\nPhone: +91 9876543210");
    }
}
