package com.example.moviereview;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView movieListView;
    private Button addReviewButton;
    private MovieDatabaseHelper dbHelper;
    private ArrayList<String> movieList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieListView = findViewById(R.id.movieListView);
        addReviewButton = findViewById(R.id.addReviewButton);
        dbHelper = new MovieDatabaseHelper(this);

        loadMovieList();

        addReviewButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddReviewActivity.class);
            startActivity(intent);
        });

        movieListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedMovie = movieList.get(position);
            Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
            intent.putExtra("movie_name", selectedMovie);
            startActivity(intent);
        });
    }
    protected void onResume() {
        super.onResume();
        loadMovieList();
    }

    private void loadMovieList() {
        movieList = new ArrayList<>();
        Cursor cursor = dbHelper.getAllMovies();
        while (cursor.moveToNext()) {
            movieList.add(cursor.getString(1)); // Movie Name
        }
        cursor.close();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, movieList);
        movieListView.setAdapter(adapter);
    }
}
