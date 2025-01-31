package com.example.linear;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RelativeActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative);

        Button linearPage = findViewById(R.id.linear_page);
        linearPage.setOnClickListener( v-> {
            Intent intent = new Intent(RelativeActivity.this, MainActivity.class);
            startActivity(intent);
                }
        );
    }
}
