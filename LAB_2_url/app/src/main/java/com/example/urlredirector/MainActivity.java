package com.example.urlredirector;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find UI elements
        EditText urlInput = findViewById(R.id.urlInput);
        Button openUrlButton = findViewById(R.id.openUrlButton);

        // Set click listener
        openUrlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = urlInput.getText().toString().trim();

                if (!url.isEmpty()) {
                    // Ensure the URL has "https://" if not provided
                    if (!url.startsWith("http://") && !url.startsWith("https://")) {
                        url = "https://" + url;
                    }

                    // Open the URL using an Intent
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a valid URL", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
