package com.example.android.vocabbuilder;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Lewis on 23/06/15.
 */
public class Vocabulary extends AppCompatActivity{
    // TODO: Vocabulary will be a list of words from the R.raw.vocabulary database
    // TODO: There needs to be a way to select a certain number of the words
    // TODO: as answers and create objects to include in a Question
    // TODO: variables: language
    //
    // Sqlite database query SELECT IMGFILE, NAME, AUDIO FROM 'Vocabulary' WHERE LANG = "EN"
    List<Word> vocabularyArrayList = new ArrayList<Word>();

    public Vocabulary(Context context){
        VocabularyDbHelper myDbHelper = new VocabularyDbHelper(context);
        SharedPreferences settings = context.getSharedPreferences("PrefsFile", MODE_PRIVATE);
        String language = settings.getString("language", "en"); // Should never have to default, but en is OK if we do




        try {

            myDbHelper.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }



        myDbHelper.openDataBase();

        int wordCount= myDbHelper.getWordCount();

        vocabularyArrayList = myDbHelper.getWordsFromDataBase(language.toUpperCase());
        myDbHelper.close();
    }


public ArrayList<Word> getn(int n) {     // returns n randomly-selected unique words from the total set
    Collections.shuffle(vocabularyArrayList);
    //Word[] words = new Word[n];
    ArrayList<Word> words = new ArrayList<>();
    for (int i = 0; i < n; i++){
        //words[i] = vocabularyArrayList.get(i);
        words.add(vocabularyArrayList.get(i));
    }
    return words;
    }


}