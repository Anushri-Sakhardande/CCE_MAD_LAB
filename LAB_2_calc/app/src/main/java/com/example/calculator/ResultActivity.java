package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Retrieve the data passed from MainActivity
        double num1 = getIntent().getDoubleExtra("num1", 0);
        double num2 = getIntent().getDoubleExtra("num2", 0);
        String operator = getIntent().getStringExtra("operator");
        double result = getIntent().getDoubleExtra("result", 0);

        // Format and display the result
        TextView resultTextView = findViewById(R.id.result_text);
        String resultText = num1 + " " + operator + " " + num2 + " = " + result;
        resultTextView.setText(resultText);

        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener( v-> {
                    // Go back to main activity
                    Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                    startActivity(intent);
               }
        );
    }
}
