package com.example.careerguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CareerDatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "careers.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE = "saved_careers";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "career_name";
    public static final String COL_DESC = "description";
    public static final String COL_DATE = "date_saved";

    public CareerDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NAME + " TEXT, " + COL_DESC + " TEXT, " + COL_DATE + " TEXT" + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public long insertCareer(String name, String desc, String date) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, name);
        cv.put(COL_DESC, desc);
        cv.put(COL_DATE, date);
        return db.insert(TABLE, null, cv);
    }

    public Cursor getAll() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(TABLE, null, null, null, null, null, COL_ID + " DESC");
    }

    public int deleteById(long id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE, COL_ID + " = ?", new String[]{String.valueOf(id)});
    }
}
