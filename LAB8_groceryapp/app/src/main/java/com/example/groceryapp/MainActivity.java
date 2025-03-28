package com.example.groceryapp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Spinner itemSpinner;
    private Button addButton, showTotalButton;
    private TextView totalCostText;
    private GroceryDatabaseHelper dbHelper;
    private Map<String, Integer> selectedItems = new HashMap<>();
    private int totalCost = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemSpinner = findViewById(R.id.itemSpinner);
        addButton = findViewById(R.id.addButton);
        showTotalButton = findViewById(R.id.showTotalButton);
        totalCostText = findViewById(R.id.totalCostText);
        dbHelper = new GroceryDatabaseHelper(this);

        loadItemsIntoSpinner();

        addButton.setOnClickListener(v -> addItem());

        showTotalButton.setOnClickListener(v -> {
            totalCost = 0;
            for (int price : selectedItems.values()) {
                totalCost += price;
            }
            totalCostText.setText("Total Cost: ₹" + totalCost);
        });
    }

    private void loadItemsIntoSpinner() {
        List<String> itemList = new ArrayList<>();
        Cursor cursor = dbHelper.getAllItems();
        while (cursor.moveToNext()) {
            String itemName = cursor.getString(1);
            itemList.add(itemName);
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, itemList);
        itemSpinner.setAdapter(adapter);
    }

    private void addItem() {
        String selectedItem = itemSpinner.getSelectedItem().toString();
        int itemCost = dbHelper.getItemCost(selectedItem);
        selectedItems.put(selectedItem, itemCost);
        Toast.makeText(this, selectedItem + " added (₹" + itemCost + ")", Toast.LENGTH_SHORT).show();
    }
}
