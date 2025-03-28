package com.example.digitaltransformation;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView menuIcon;
    private EditText searchInput;
    private TextView descriptionText;
    private String originalText = "Digital Transformation is the process of integrating digital technology into all areas of business, fundamentally changing how organizations operate and deliver value to customers.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuIcon = findViewById(R.id.menuIcon);
        searchInput = findViewById(R.id.searchInput);
        descriptionText = findViewById(R.id.descriptionText);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v);
            }
        });
    }

    private void showPopupMenu(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.getMenuInflater().inflate(R.menu.filter_menu, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.search_keyword) {
                    searchInput.setVisibility(View.VISIBLE);
                    searchInput.setOnEditorActionListener((v, actionId, event) -> {
                        String keyword = searchInput.getText().toString();
                        if (!keyword.isEmpty()) {
                            searchKeyword(keyword);
                        }
                        return true;
                    });
                    return true;
                }

                if (itemId == R.id.highlight_text) {
                    searchInput.setVisibility(View.VISIBLE);
                    searchInput.setHint("Enter word to highlight");
                    searchInput.setOnEditorActionListener((v, actionId, event) -> {
                        String wordToHighlight = searchInput.getText().toString();
                        if (!wordToHighlight.isEmpty()) {
                            highlightText(wordToHighlight);
                        }
                        return true;
                    });
                    return true;
                }

                if (itemId == R.id.sort_text) {
                    sortTextAlphabetically();
                    return true;
                }

                return false;
            }
        });

        popup.show();
    }

    private void searchKeyword(String keyword) {
        if (originalText.toLowerCase().contains(keyword.toLowerCase())) {
            Toast.makeText(this, "Keyword Found!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Keyword Not Found!", Toast.LENGTH_SHORT).show();
        }
    }

    private void highlightText(String word) {
        SpannableString spannableString = new SpannableString(originalText);
        int start = originalText.toLowerCase().indexOf(word.toLowerCase());

        if (start != -1) {
            spannableString.setSpan(new BackgroundColorSpan(Color.YELLOW), start, start + word.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            descriptionText.setText(spannableString);
        } else {
            Toast.makeText(this, "Word not found!", Toast.LENGTH_SHORT).show();
        }
    }

    private void sortTextAlphabetically() {
        List<String> words = Arrays.asList(originalText.split(" "));
        Collections.sort(words);
        String sortedText = String.join(" ", words);
        descriptionText.setText(sortedText);
    }
}
