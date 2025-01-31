package com.example.layouts;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class GridActivity extends AppCompatActivity {

    // Array of image resources
    private final Integer[] imageIds = {
            R.drawable.image1, R.drawable.image2, R.drawable.image3,
            R.drawable.image4, R.drawable.image5, R.drawable.image6,
            R.drawable.image7, R.drawable.image8, R.drawable.image9,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return imageIds.length;
            }

            @Override
            public Object getItem(int position) {
                return imageIds[position];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView imageView;
                if (convertView == null) {
                    imageView = new ImageView(GridActivity.this);
                    imageView.setLayoutParams(new GridView.LayoutParams(200, 200)); // Image size
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    imageView.setPadding(5, 5, 5, 5);
                } else {
                    imageView = (ImageView) convertView;
                }

                imageView.setImageResource(imageIds[position]);
                return imageView;
            }
        });
    }
}