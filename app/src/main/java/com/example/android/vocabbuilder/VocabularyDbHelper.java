package com.example.android.vocabbuilder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lewis on 24/06/15.
 */
public class VocabularyDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    // TODO: enable database to be read into an array
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "vocabulary.db";

    public VocabularyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over

        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}