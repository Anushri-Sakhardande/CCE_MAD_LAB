package com.example.midsem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TabFragment extends Fragment {
    private String content;
    public TabFragment(String content) {
        this.content = content;
    }
    @Override
    public View onCreateView( LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);

        // Set the content to the TextView
        TextView textView = view.findViewById(R.id.textView);
        textView.setText(content);

        return view;
    }
}
