package com.gmail.appytalkteam.appytalkcore;

import java.util.ArrayList;

/**
 * This class maintains a Quiz object for the duration of one quiz,
 * and returns Questions on demand.
 * Created by derwin on 09/07/15.
 */
public class Quiz {

    private ArrayList<Question> quiz;
    private int numberOfQuestions;
    private int currentQuestion;

    public Quiz(int numberOfQuestions, int numberOfOptions, Vocabulary vocab){
        ArrayList<Question> myQs = new ArrayList<>();
        this.numberOfQuestions = numberOfQuestions;
        for(int i = 0; i < this.numberOfQuestions; i++){
            Question addme = new Question(numberOfOptions, vocab);
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
        return currentQuestion < numberOfQuestions; // If false, we've overshot!
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
