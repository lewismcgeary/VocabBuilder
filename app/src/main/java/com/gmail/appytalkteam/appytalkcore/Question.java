package com.gmail.appytalkteam.appytalkcore;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class gets one Question.
 * A Question has 3 Words, a correct Answer, and a boolean[] of which guesses have been made
 * Created by derwin on 09/07/15.
 */
public class Question {
    private Context myCtx;
    private ArrayList<Word> words;
    private int Answer;
    private boolean[] guessed;

    public Question(Context ctx, Vocabulary vocab){
        Random rn = new Random();
        this.myCtx = ctx;
        Resources res = myCtx.getResources();//Resources.getSystem();
        int nOptions = res.getInteger(R.integer.numberOfChoices);
        words = vocab.getRandomWords(nOptions);
        Answer = rn.nextInt(nOptions);
        guessed = new boolean[nOptions];
        for(int i = 0; i <nOptions; i++) guessed[i] = false;
    }
    // category overload for Question
    public Question(Context ctx, Vocabulary vocab, String category){
        Random rn = new Random();
        this.myCtx = ctx;
        Resources res = myCtx.getResources();//Resources.getSystem();
        int nOptions = res.getInteger(R.integer.numberOfChoices);
        words = vocab.getRandomWords(nOptions, category);
        Answer = rn.nextInt(nOptions);
        guessed = new boolean[nOptions];
        for(int i = 0; i <nOptions; i++) guessed[i] = false;
    }
    public ArrayList<Word> getWords() {
        return words;
    }
    public int getAnswer(){
        return Answer;
    }
    public boolean isGuessed(int i){
        return guessed[i];
    }
    public void setGuessed(int i, boolean b){
        guessed[i] = b;
    }
    public boolean[] guessed(){
        return guessed;
    }
    public Word getCorrectWord(){
        return words.get(Answer);
    }

}
