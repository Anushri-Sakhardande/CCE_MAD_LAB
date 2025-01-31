package com.example.news;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainFragment extends Fragment {
    // Initialize variable
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Initialize view
        View view = inflater.inflate(R.layout.fragment_main, container, false);

// Assign variable
        ListView listView = view.findViewById(R.id.listview);

// Get Title from arguments
        String sTitle = getArguments().getString("title");

// Define sample data for each category
        String[] items;

        if ("  Top Stories".equals(sTitle)) {
            items = new String[]{"Breaking News 1", "Breaking News 2", "Breaking News 3"};
        } else if ("  Sports".equals(sTitle)) {
            items = new String[]{"Football", "Cricket", "Basketball", "Tennis", "Hockey"};
        } else if ("  Entertainment".equals(sTitle)) {
            items = new String[]{"Movie Release", "Celebrity News", "Music Charts"};
        } else {
            items = new String[]{"No Data Available"};
        }

// Set adapter to ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                items
        );
        listView.setAdapter(adapter);

// Return view
        return view;

    }
}
