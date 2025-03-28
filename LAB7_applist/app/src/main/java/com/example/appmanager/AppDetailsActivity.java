package com.example.appmanager;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;

public class AppDetailsActivity extends AppCompatActivity {

    private TextView txtAppName, txtPackageName, txtVersion, txtPermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_details);

        txtAppName = findViewById(R.id.txtAppName);
        txtPackageName = findViewById(R.id.txtPackageName);
        txtVersion = findViewById(R.id.txtVersion);
        txtPermissions = findViewById(R.id.txtPermissions);

        Intent intent = getIntent();
        String packageName = intent.getStringExtra("packageName");

        if (packageName != null) {
            showAppDetails(packageName);
        }
    }

    private void showAppDetails(String packageName) {
        PackageManager pm = getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);
            ApplicationInfo appInfo = pm.getApplicationInfo(packageName, 0);

            txtAppName.setText(pm.getApplicationLabel(appInfo));
            txtPackageName.setText(packageName);
            txtVersion.setText("Version: " + packageInfo.versionName);

            String[] permissions = packageInfo.requestedPermissions;
            txtPermissions.setText("Permissions: " + (permissions != null ? Arrays.toString(permissions) : "None"));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
