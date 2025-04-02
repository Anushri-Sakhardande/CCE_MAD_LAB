package com.example.groceryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GroceryDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "grocery.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_NAME = "grocery_items";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "item_name";
    private static final String COLUMN_COST = "item_cost";
    private static final String COLUMN_CATEGORY = "item_category";

    public GroceryDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_COST + " INTEGER, " +
                COLUMN_CATEGORY + " TEXT)";
        db.execSQL(createTable);

        insertInitialData(db);
    }

    private void insertInitialData(SQLiteDatabase db) {
        // Insert Veggies
        insertItem(db, "Potato", 30, "Veggies");
        insertItem(db, "Tomato", 40, "Veggies");
        insertItem(db, "Carrot", 25, "Veggies");

        // Insert Dairy
        insertItem(db, "Milk", 50, "Dairy");
        insertItem(db, "Cheese", 80, "Dairy");
        insertItem(db, "Butter", 90, "Dairy");

        // Insert Fruits
        insertItem(db, "Apple", 100, "Fruits");
        insertItem(db, "Banana", 60, "Fruits");
        insertItem(db, "Grapes", 120, "Fruits");
    }

    private void insertItem(SQLiteDatabase db, String name, int cost, String category) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_COST, cost);
        values.put(COLUMN_CATEGORY, category);
        db.insert(TABLE_NAME, null, values);
    }

    public Cursor getAllItems() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public int getItemCost(String itemName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_COST + " FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + "=?", new String[]{itemName});
        if (cursor.moveToFirst()) {
            int cost = cursor.getInt(0);
            cursor.close();
            return cost;
        }
        cursor.close();
        return 0;
    }

    public int getCategoryTotal(String category) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + COLUMN_COST + ") FROM " + TABLE_NAME + " WHERE " + COLUMN_CATEGORY + "=?", new String[]{category});
        int total = 0;
        if (cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }
        cursor.close();
        return total;
    }

    public int getGrandTotal() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + COLUMN_COST + ") FROM " + TABLE_NAME, null);
        int total = 0;
        if (cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }
        cursor.close();
        return total;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
