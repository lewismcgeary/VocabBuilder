package com.gmail.appytalkteam.appytalkcore;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Lewis on 19/07/15.
 */
public class VocabularyDbHelperWithAssetHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "vocabulary.sqlite";
    private static final int DATABASE_VERSION = 1;

    public VocabularyDbHelperWithAssetHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void createDataBase(){
        getReadableDatabase();
    }
}
