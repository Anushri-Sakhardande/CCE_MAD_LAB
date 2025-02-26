package com.example.food_order;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    TextView textViewOrderList, textViewTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        textViewOrderList = findViewById(R.id.textViewOrderList);
        textViewTotal = findViewById(R.id.textViewTotal);

        ArrayList<String> selectedItems = getIntent().getStringArrayListExtra("selectedItems");
        int totalCost = getIntent().getIntExtra("totalCost", 0);

        if (selectedItems != null && !selectedItems.isEmpty()) {
            StringBuilder orderDetails = new StringBuilder();
            for (String item : selectedItems) {
                orderDetails.append(item).append("\n");
            }
            textViewOrderList.setText(orderDetails.toString());
            textViewTotal.setText("Total Cost: ₹" + totalCost);
        } else {
            textViewOrderList.setText("No items selected.");
            textViewTotal.setText("");
        }
    }
}
