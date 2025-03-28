package com.example.appmanager;

public class AppModel {
    private String name, packageName;
    private boolean isSystemApp;

    public AppModel(String name, String packageName, boolean isSystemApp) {
        this.name = name;
        this.packageName = packageName;
        this.isSystemApp = isSystemApp;
    }

    public String getName() { return name; }
    public String getPackageName() { return packageName; }
    public boolean isSystemApp() { return isSystemApp; }
}
