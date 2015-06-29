package com.example.android.vocabbuilder;

/**
 * Created by Lewis on 23/06/15.
 */
public class Vocabulary {
    // TODO: Vocabulary will be a list of words from the R.raw.vocabulary database
    // TODO: There needs to be a way to select a certain number of the words
    // TODO: as answers and create objects to include in a Question
    // TODO: variables: language
public Word[] getn(int n) {     // returns n randomly-selected unique words from the total set
    Word[] words =  {
            new Word("cat","sample_cat","cat.mp3"), // we chose this at random
            new Word("car","sample_car","car.mp3"),
            new Word("dog","sample_dog","dog.mp3")
    };
    return words;
    }
}
