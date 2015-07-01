package com.example.android.vocabbuilder;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lewis on 23/06/15.
 */
public class Vocabulary {
    // TODO: Vocabulary will be a list of words from the R.raw.vocabulary database
    // TODO: There needs to be a way to select a certain number of the words
    // TODO: as answers and create objects to include in a Question
    // TODO: variables: language
    //
    // Sqlite database query SELECT IMGFILE, NAME, AUDIO FROM 'Vocabulary' WHERE LANG = "EN"
    List<Word> vocabularyArrayList = new ArrayList<Word>();

    public Vocabulary(Context context){
        VocabularyDbHelper myDbHelper = new VocabularyDbHelper(context);


        try {

            myDbHelper.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }



        myDbHelper.openDataBase();

        int wordCount= myDbHelper.getWordCount();

        vocabularyArrayList = myDbHelper.getWordsFromDataBase("EN");
    }


public Word[] getn(int n) {     // returns n randomly-selected unique words from the total set
    Word[] words = new Word[n];
    for (int i = 0; i < n; i++){
        words[i] = vocabularyArrayList.get(i);
    }
    return words;
    }


/*public Word[] scramble() {
    return words;
    }*/
}