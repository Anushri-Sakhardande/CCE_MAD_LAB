package com.example.appmanager;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<AppModel> appList;
    private AppAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        appList = getInstalledApps();
        adapter = new AppAdapter(this, appList);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showPopupMenu(view, position);
                return true;
            }
        });
    }

    private List<AppModel> getInstalledApps() {
        List<AppModel> apps = new ArrayList<>();
        PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(0);

        for (ApplicationInfo app : packages) {
            String name = (String) pm.getApplicationLabel(app);
            String packageName = app.packageName;
            boolean isSystem = (app.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
            apps.add(new AppModel(name, packageName, isSystem));
        }
        return apps;
    }

    private void showPopupMenu(View view, int position) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.getMenuInflater().inflate(R.menu.app_options_menu, popup.getMenu());

        popup.setOnMenuItemClickListener(item -> {
            AppModel app = appList.get(position);
            int itemId = item.getItemId();
            if (itemId == R.id.option_open) {
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage(app.getPackageName());
                if (launchIntent != null) startActivity(launchIntent);

            }
            if (itemId == R.id.option_uninstall) {
                Intent uninstallIntent = new Intent(Intent.ACTION_DELETE);
                uninstallIntent.setData(Uri.parse("package:" + app.getPackageName()));
                startActivity(uninstallIntent);
            }
            if (itemId == R.id.option_details) {
                Intent detailsIntent = new Intent(this, AppDetailsActivity.class);
                detailsIntent.putExtra("packageName", app.getPackageName());
                startActivity(detailsIntent);
            }
            return true;
        });

        popup.show();
    }
}
