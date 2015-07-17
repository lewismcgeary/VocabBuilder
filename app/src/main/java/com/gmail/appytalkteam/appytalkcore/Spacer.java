package com.gmail.appytalkteam.appytalkcore;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

/**
 * This is supposed to implement semi-intelligent spaced repetition and dole out
 * words the user doesn't know so well
 * Created by derwin on 17/07/15.
 */

// this class incorporates how well we know the word plus
// The probability (which depends on how well we know it)
public class Spacer {
    Context mcontext;
    ArrayList<UserWord> uservocab;
    ArrayList<UserWord> remainingvocab;

    public Spacer(Context context) {
        readVocab(context);
        mcontext = context;
    }

    public void questionResult(Word word, int tries) {
        UserWord curword = findUserWord(word);
        curword.setResult(2 - tries); // if three tries, -1, two tries 0, one try 1
        if(tries == 1) getNewWord();
        uservocab = renormaliseProbabilities(uservocab);
    }

    public UserWord findUserWord(Word wordRequested){
        // iterate through uservocab until the imageLocation matches
        UserWord uWord = new UserWord("bum","bum","bum","bum");
        for(UserWord word: uservocab){
            if(word.imageLocation.equals(wordRequested.imageLocation)) uWord = word;
        }
        return uWord;
    }

    public ArrayList<UserWord> renormaliseProbabilities(ArrayList<UserWord> input){
        double total = 0.0;
        ArrayList<UserWord> output = new ArrayList<>();
        for(UserWord word: input){
            word.probability = 1/word.box;
            output.add(word);
            total += word.probability;
        }
        Iterator<UserWord> iter = output.iterator();
        while (iter.hasNext()) {
            iter.next().probability /= total;
        }
        return output;
    }

    private void readVocab(Context context) {
        SpacerDbHelper mydb = new SpacerDbHelper(context);
        uservocab = mydb.getAllSeen();
        remainingvocab = mydb.getAllUnseen();
    }

    public void getNewWord(){
        uservocab.add(remainingvocab.get(0));
        remainingvocab.remove(0);
    }

    // This method gets BIASED random words from the user vocabulary, where p = C*1/box
    public ArrayList<Word> getRandomWords(int n) {
        // return n words from uservocab
        ArrayList<UserWord> filteredUserVocab = renormaliseProbabilities(selectByCategory(uservocab));
        ArrayList<Word> output = new ArrayList<>();
        Random rn = new Random();
        float myVal = rn.nextFloat();
        float probSum = (float) 0.0;
        Collections.shuffle(filteredUserVocab);
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < filteredUserVocab.size(); i++) {
                probSum += filteredUserVocab.get(i).probability;
                if (myVal <= probSum) {
                    output.add(filteredUserVocab.get(i).asWord());
                }
            }
        }
        return output;
    }

    public ArrayList<UserWord> selectByCategory(ArrayList<UserWord> input){
        ArrayList<UserWord> output = new ArrayList<>();
        String currentcategory = mcontext.getSharedPreferences("PrefsFile",Context.MODE_PRIVATE).getString("category","Food");
        for(UserWord uwrd: input){
            if(uwrd.category.equals(currentcategory)){
                output.add(uwrd);
            }
        }
        return output;
    }
}
