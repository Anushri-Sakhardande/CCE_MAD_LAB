package com.example.xyzfitnesscenter;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class MembershipActivity extends AppCompatActivity {

    private ListView listView;
    private String[] membershipPlans = {"Monthly - ₹999", "Quarterly - ₹2499", "Yearly - ₹7999"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership);

        listView = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, membershipPlans);
        listView.setAdapter(adapter);
    }
}
