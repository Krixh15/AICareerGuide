package com.example.careerguide;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

/**
 * Minimal educational ContentProvider exposing saved careers.
 * This is a simple wrapper around CareerDatabaseHelper. Only a minimal
 * implementation is provided for syllabus demonstration.
 */
public class CareerProvider extends ContentProvider {

    private CareerDatabaseHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new CareerDatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return dbHelper.getAll();
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // Not implemented for simplicity
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }
}
