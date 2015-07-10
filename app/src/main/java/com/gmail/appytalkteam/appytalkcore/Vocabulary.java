package com.gmail.appytalkteam.appytalkcore;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class to create and contain arraylist of Word objects based on a database query
 */
public class Vocabulary{

    private ArrayList<Word> vocabularyArrayList = new ArrayList<>();

    // When new Vocabulary object is created, add Word objects to vocabularyArrayList.
    // Word objects are based on available entries for the selected language in vocabulary.sqlite
    // database
    public Vocabulary(Context context){
        VocabularyDbHelper myDbHelper = new VocabularyDbHelper(context);
        SharedPreferences settings = context.getSharedPreferences("PrefsFile", Context.MODE_PRIVATE);
        String language = settings.getString("language", "en"); // Should never have to default, but en is OK if we do
        //noinspection
        vocabularyArrayList = myDbHelper.getWordsFromDataBase(language.toUpperCase());
    }

    // returns n randomly-selected unique words from the total set
public ArrayList<Word> getRandomWords(int n) {
    // shuffle order of vocabulary words
    Collections.shuffle(vocabularyArrayList);
    ArrayList<Word> words = new ArrayList<>();
    // add selected number of words to arraylist
    for (int i = 0; i < n; i++){
        words.add(vocabularyArrayList.get(i));
    }
    return words;
    }


}