package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText num1EditText, num2EditText;
    private RadioButton addRadio, subtractRadio, multiplyRadio, divideRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1EditText = findViewById(R.id.num1);
        num2EditText = findViewById(R.id.num2);
        addRadio = findViewById(R.id.radio_add);
        subtractRadio = findViewById(R.id.radio_subtract);
        multiplyRadio = findViewById(R.id.radio_multiply);
        divideRadio = findViewById(R.id.radio_divide);

        Button calculateButton = findViewById(R.id.calculate_button);
        calculateButton.setOnClickListener(v -> {
            String num1Str = num1EditText.getText().toString();
            String num2Str = num2EditText.getText().toString();

            if (num1Str.isEmpty() || num2Str.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
                return;
            }

            double num1 = Double.parseDouble(num1Str);
            double num2 = Double.parseDouble(num2Str);
            String operator = "";
            double result = 0;

            if (addRadio.isChecked()) {
                operator = "+";
                result = num1 + num2;
            } else if (subtractRadio.isChecked()) {
                operator = "-";
                result = num1 - num2;
            } else if (multiplyRadio.isChecked()) {
                operator = "*";
                result = num1 * num2;
            } else if (divideRadio.isChecked()) {
                if (num2 == 0) {
                    Toast.makeText(MainActivity.this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                    return;
                }
                operator = "/";
                result = num1 / num2;
            } else {
                Toast.makeText(MainActivity.this, "Please select an operator", Toast.LENGTH_SHORT).show();
                return;
            }

            // Send data to ResultActivity
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("num1", num1);
            intent.putExtra("num2", num2);
            intent.putExtra("operator", operator);
            intent.putExtra("result", result);
            startActivity(intent);
        });
    }
}
