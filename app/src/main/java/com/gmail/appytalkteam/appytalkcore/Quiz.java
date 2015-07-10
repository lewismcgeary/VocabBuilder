package com.gmail.appytalkteam.appytalkcore;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;

/**
 * This class maintains a Quiz object for the duration of one quiz,
 * and returns Questions on demand.
 * Created by derwin on 09/07/15.
 */
public class Quiz {

private Context myCtx;
ArrayList<Question> quiz;
    int nQuestions;
    int currentQuestion;

    public Quiz(Context context, Vocabulary vocab){
        ArrayList<Question> myQs = new ArrayList<>();
        this.myCtx = context;
        Resources res = myCtx.getResources();
        nQuestions = res.getInteger(R.integer.numberOfQuestions);
        for(int i = 0; i < nQuestions; i++){
            Question addme = new Question(myCtx,vocab);
            myQs.add(addme);
        }
        quiz = myQs;
        currentQuestion = 0;
    }

    public Question getQuestion(int n){
        return quiz.get(n);
    }

    public Question getCurrentQuestion(){
        return quiz.get(currentQuestion);
    }

    public boolean incrementQuestionNumber(){
        currentQuestion++;
        return currentQuestion < nQuestions; // If false, we've overshot!
    }

    public int getQuestionNumber(){
        return currentQuestion;
    }

    // We need to know all the answers so that we can load sounds in advance
    public ArrayList<Word> getAllAnswers(){
        ArrayList<Word> mylist = new ArrayList<>();
        for(Question question: quiz){
            mylist.add(question.getWords().get(question.getAnswer()));
        }
        return mylist;
    }
}
