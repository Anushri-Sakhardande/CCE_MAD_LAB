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

    private Spinner categorySpinner, itemSpinner;
    private Button addButton, showTotalButton;
    private TextView totalCostText;
    private GroceryDatabaseHelper dbHelper;
    private Map<String, Integer> selectedItems = new HashMap<>();
    private Map<String, Integer> categoryTotals = new HashMap<>();
    private int grandTotal = 0;

    private Map<String, List<String>> categoryItems = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categorySpinner = findViewById(R.id.categorySpinner);
        itemSpinner = findViewById(R.id.itemSpinner);
        addButton = findViewById(R.id.addButton);
        showTotalButton = findViewById(R.id.showTotalButton);
        totalCostText = findViewById(R.id.totalCostText);
        dbHelper = new GroceryDatabaseHelper(this);

        loadCategories();
        loadItemsByCategory();

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loadItemsIntoSpinner(categorySpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        addButton.setOnClickListener(v -> addItem());
        showTotalButton.setOnClickListener(v -> calculateTotal());
    }

    private void loadCategories() {
        List<String> categories = new ArrayList<>();
        categories.add("Veggies");
        categories.add("Dairy");
        categories.add("Fruits");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        categorySpinner.setAdapter(adapter);
    }

    private void loadItemsByCategory() {
        categoryItems.put("Veggies", new ArrayList<>());
        categoryItems.put("Dairy", new ArrayList<>());
        categoryItems.put("Fruits", new ArrayList<>());

        Cursor cursor = dbHelper.getAllItems();
        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            String category = cursor.getString(3);
            categoryItems.get(category).add(name);
        }
        cursor.close();
    }

    private void loadItemsIntoSpinner(String category) {
        List<String> items = categoryItems.getOrDefault(category, new ArrayList<>());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        itemSpinner.setAdapter(adapter);
    }

    private void addItem() {
        String category = categorySpinner.getSelectedItem().toString();
        String selectedItem = itemSpinner.getSelectedItem().toString();
        int itemCost = dbHelper.getItemCost(selectedItem);

        selectedItems.put(selectedItem, itemCost);
        categoryTotals.put(category, categoryTotals.getOrDefault(category, 0) + itemCost);

        Toast.makeText(this, selectedItem + " added (₹" + itemCost + ")", Toast.LENGTH_SHORT).show();
    }

    private void calculateTotal() {
        grandTotal = 0;
        StringBuilder details = new StringBuilder();

        for (Map.Entry<String, Integer> entry : categoryTotals.entrySet()) {
            details.append(entry.getKey()).append(" Total: ₹").append(entry.getValue()).append("\n");
            grandTotal += entry.getValue();
        }

        details.append("Grand Total: ₹").append(grandTotal);
        totalCostText.setText(details.toString());
    }
}