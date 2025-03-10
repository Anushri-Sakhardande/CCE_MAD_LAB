package com.example.food_order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    CheckBox chkPizza, chkBurger, chkPasta, chkSandwich;
    Button btnSubmit;
    boolean orderSubmitted = false; // Flag to prevent changes after submission

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chkPizza = findViewById(R.id.chkPizza);
        chkBurger = findViewById(R.id.chkBurger);
        chkPasta = findViewById(R.id.chkPasta);
        chkSandwich = findViewById(R.id.chkSandwich);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(v -> {
            if (!orderSubmitted) { // Check if order is already submitted
                ArrayList<String> selectedItems = new ArrayList<>();
                int totalCost = 0;

                if (chkPizza.isChecked()) {
                    selectedItems.add("Pizza - ₹200");
                    totalCost += 200;
                }
                if (chkBurger.isChecked()) {
                    selectedItems.add("Burger - ₹100");
                    totalCost += 100;
                }
                if (chkPasta.isChecked()) {
                    selectedItems.add("Pasta - ₹150");
                    totalCost += 150;
                }
                if (chkSandwich.isChecked()) {
                    selectedItems.add("Sandwich - ₹80");
                    totalCost += 80;
                }

                // Disable checkboxes to prevent changes
                chkPizza.setEnabled(false);
                chkBurger.setEnabled(false);
                chkPasta.setEnabled(false);
                chkSandwich.setEnabled(false);
                orderSubmitted = true; // Prevent further changes

                // Start new activity with selected items
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                intent.putStringArrayListExtra("selectedItems", selectedItems);
                intent.putExtra("totalCost", totalCost);
                startActivity(intent);
            }
        });
    }
}
