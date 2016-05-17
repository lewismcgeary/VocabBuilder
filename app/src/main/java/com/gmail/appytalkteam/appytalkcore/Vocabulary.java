package com.gmail.appytalkteam.appytalkcore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Class to create and contain arraylist of Word objects based on a database query
 */
public class Vocabulary{

    private ArrayList<Word> vocabularyArrayList = new ArrayList<>();

    // When new Vocabulary object is created, add Word objects to vocabularyArrayList.
    // Word objects are based on available entries for the selected language in vocabulary.sqlite
    // database
    public Vocabulary(VocabularyDbHelper myDbHelper){
        //noinspection
        vocabularyArrayList = myDbHelper.getWordsFromDataBase();
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
    public ArrayList<Word> getRandomWords(int n, String category) {
        // shuffle order of vocabulary words
        Collections.shuffle(vocabularyArrayList);
        ArrayList<Word> filtered = vocabularyArrayList; // We copy this so that we can leave vocabularyArrayList alone
        ArrayList<Word> words = new ArrayList<>();
        //filter the words for category
        Iterator<Word> it = filtered.iterator();
        while( it.hasNext() ) {
            Word foo = it.next();
            if( foo.getCategory()!=category ) it.remove();
        }
        // add selected number of words to arraylist
        for (int i = 0; i < n; i++){
            words.add(filtered.get(i));
        }
        return words;
    }



}