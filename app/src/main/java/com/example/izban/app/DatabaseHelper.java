package com.example.izban.app;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by izban on 17.01.15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String NAME = "data";
    public static final int ver = 1;

    public static final String IMAGES_TABLE_NAME = "cities";
    public static final String IMAGES_ID = "_ID";
    public static final String IMAGES_LINK = "link";
    public static final String IMAGES_CREATE =
            "CREATE TABLE " + IMAGES_TABLE_NAME + " (" +
                    IMAGES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    IMAGES_LINK + " TEXT)";


    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public DatabaseHelper(Context context) {
        super(context, NAME, null, ver);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("", "creating db");
        db.execSQL(IMAGES_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + IMAGES_TABLE_NAME);
        onCreate(db);
    }

    static public MyImage getImage(Cursor cursor) {
        return new MyImage(cursor.getString(cursor.getColumnIndex(IMAGES_LINK)));
    }
}