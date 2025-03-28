package com.example.mymenu;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView menuIcon, imageDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuIcon = findViewById(R.id.menuIcon);
        imageDisplay = findViewById(R.id.imageDisplay);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v);
            }
        });
    }

    private void showPopupMenu(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.getMenuInflater().inflate(R.menu.my_menu, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                if(itemId== R.id.image_1) {
                    imageDisplay.setImageResource(R.drawable.image1); // Add image1 in res/drawable
                    imageDisplay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Image -1 Selected", Toast.LENGTH_SHORT).show();
                }
                if(itemId==R.id.image_2){
                        imageDisplay.setImageResource(R.drawable.image2); // Add image2 in res/drawable
                        imageDisplay.setVisibility(View.VISIBLE);
                        Toast.makeText(MainActivity.this, "Image -2 Selected", Toast.LENGTH_SHORT).show();

                }
                return true;
            }
        });

        popup.show();
    }
}
