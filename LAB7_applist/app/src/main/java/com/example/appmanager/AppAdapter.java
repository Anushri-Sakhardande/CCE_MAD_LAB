package com.example.appmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import java.util.List;

public class AppAdapter extends ArrayAdapter<AppModel> {

    public AppAdapter(Context context, List<AppModel> apps) {
        super(context, 0, apps);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_app, parent, false);
        }

        AppModel app = getItem(position);
        TextView txtAppName = convertView.findViewById(R.id.txtAppName);
        txtAppName.setText(app.getName());

        return convertView;
    }
}
