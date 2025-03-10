package com.example.xyzfitnesscenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private String[] options = {"Workout Plans", "Trainers", "Membership"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() == null) {
            setSupportActionBar(new Toolbar(this));
        }

        listView = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, options);
        listView.setAdapter(adapter);

        // Handle list item clicks
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent;
            if (position == 0) {
                intent = new Intent(MainActivity.this, WorkoutPlansActivity.class);
            } else if (position == 1) {
                intent = new Intent(MainActivity.this, TrainersActivity.class);
            } else {
                intent = new Intent(MainActivity.this, MembershipActivity.class);
            }
            startActivity(intent);
        });
    }

    // Inflate the options menu with icons
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    // Handle option menu clicks
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        int itemId = item.getItemId();
        if (itemId == R.id.contact_us) {
            intent = new Intent(this, ContactUsActivity.class);
        } else if (itemId == R.id.about_us) {
            intent = new Intent(this, AboutUsActivity.class);
        } else if (itemId == R.id.home) {
            intent = new Intent(this, MainActivity.class);
        } else {
            return super.onOptionsItemSelected(item);
        }
        startActivity(intent);
        return true;
    }
}
