package com.example.android.vocabbuilder;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Configuration;

import java.util.ArrayList;
import java.util.Random;

/**
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
        for(int i = 0; i <nOptions; i++) guessed[i] = false;
    }

    public int getAnswer(){
        return Answer;
    }


}
