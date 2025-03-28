package com.example.moviereview;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddReviewActivity extends AppCompatActivity {

    private EditText movieNameInput, movieYearInput;
    private RatingBar ratingBar;
    private Button saveButton;
    private MovieDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        movieNameInput = findViewById(R.id.movieNameInput);
        movieYearInput = findViewById(R.id.movieYearInput);
        ratingBar = findViewById(R.id.ratingBar);
        saveButton = findViewById(R.id.saveButton);
        dbHelper = new MovieDatabaseHelper(this);

        saveButton.setOnClickListener(v -> saveReview());
    }

    private void saveReview() {
        String name = movieNameInput.getText().toString().trim();
        String year = movieYearInput.getText().toString().trim();
        float rating = ratingBar.getRating();

        if (name.isEmpty() || year.isEmpty()) {
            Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show();
            return;
        }

        dbHelper.insertMovie(name, year, rating);
        Toast.makeText(this, "Review Added!", Toast.LENGTH_SHORT).show();
        finish(); // Go back to MainActivity
    }
}
