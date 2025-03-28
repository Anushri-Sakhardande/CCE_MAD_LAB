package com.example.groceryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GroceryDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "grocery.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "grocery_items";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "item_name";
    private static final String COLUMN_COST = "item_cost";

    public GroceryDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_COST + " INTEGER)";
        db.execSQL(createTable);

        insertInitialData(db);
    }

    private void insertInitialData(SQLiteDatabase db) {
        insertItem(db, "Rice", 50);
        insertItem(db, "Milk", 30);
        insertItem(db, "Eggs", 80);
        insertItem(db, "Bread", 40);
        insertItem(db, "Sugar", 45);
    }

    private void insertItem(SQLiteDatabase db, String name, int cost) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_COST, cost);
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
            return cursor.getInt(0);
        }
        cursor.close();
        return 0;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
