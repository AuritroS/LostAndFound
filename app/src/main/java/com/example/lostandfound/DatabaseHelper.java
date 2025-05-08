package com.example.lostandfound;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "LostFoundDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "items";

    // Column names
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PHONE = "phone";
    private static final String DESCRIPTION = "description";
    private static final String DATE = "date";
    private static final String LOCATION = "location";
    private static final String STATUS = "status";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT, " +
                PHONE + " TEXT, " +
                DESCRIPTION + " TEXT, " +
                DATE + " TEXT, " +
                LOCATION + " TEXT, " +
                STATUS + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addItem(String name, String phone, String description, String date, String location, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, name);
        values.put(PHONE, phone);
        values.put(DESCRIPTION, description);
        values.put(DATE, date);
        values.put(LOCATION, location);
        values.put(STATUS, status);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<ItemModel> getAllItems() {
        ArrayList<ItemModel> items = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(NAME));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow(PHONE));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(DATE));
                String location = cursor.getString(cursor.getColumnIndexOrThrow(LOCATION));
                String status = cursor.getString(cursor.getColumnIndexOrThrow(STATUS));

                items.add(new ItemModel(id, name, phone, description, date, location, status));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return items;
    }

    public void deleteItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
}
