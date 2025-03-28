package com.example.moviereview;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MovieDetailsActivity extends AppCompatActivity {

    private TableLayout tableLayout;
    private MovieDatabaseHelper dbHelper;
    private String movieName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        tableLayout = findViewById(R.id.tableLayout);
        dbHelper = new MovieDatabaseHelper(this);
        movieName = getIntent().getStringExtra("movie_name");

        displayMovieDetails();
    }

    private void displayMovieDetails() {
        Cursor cursor = dbHelper.getMovieDetails(movieName);
        if (cursor.moveToFirst()) {
            String year = cursor.getString(2);
            float rating = cursor.getFloat(3);

            addTableRow("Movie Name:", movieName);
            addTableRow("Year:", year);
            addTableRow("Rating:", String.valueOf(rating));
        }
        cursor.close();
    }

    private void addTableRow(String label, String value) {
        TableRow row = new TableRow(this);
        TextView labelView = new TextView(this);
        TextView valueView = new TextView(this);

        labelView.setText(label);
        valueView.setText(value);
        row.addView(labelView);
        row.addView(valueView);

        tableLayout.addView(row);
    }
}
