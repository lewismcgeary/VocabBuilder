package com.example.android.vocabbuilder;

import android.content.Context;

import java.util.ArrayList;
import java.util.Random;

/**
 * This get()s questionSize*nQuestions words, and releases them to QuestionFragment in an orderly fashion
 * Created by derwin on 03/07/15.
 */


public class QuizMaster {
    private Context qmcontext;
    int nQuestions;
    int questionSize;
    private Word[][] quiz;
    private int[] answers;
    int counter;
    private static QuizMaster _instance;

    private QuizMaster()
    {

    }

    public static QuizMaster get_instance() {
        if(_instance == null){
        _instance = new QuizMaster();
        }
        return _instance;
    }

    // This initialises a new quiz and is called every time we start a
    private void newQuiz(Context context, int nQ, int qSize){
        // get all the words from Vocabulary
        counter = 0;
        qmcontext = context;
        int nQuestions = nQ;
        int questionSize = qSize;
        Vocabulary vocab = new Vocabulary(context.getApplicationContext()); // cut , language.toUpperCase()
        quiz = new Word[nQuestions][questionSize];
        answers = new int[nQuestions];
        Random rn = new Random();

        // place them into quiz[][] (and select random answers
        for (int i = 0; i < nQuestions ; i++){
            ArrayList q = vocab.getRandomWords(questionSize);
            for(int j = 0; j < questionSize; j++){
                quiz[i][j] = (Word) q.get(j);
            }
            answers[i] = rn.nextInt(questionSize);
        }
    }

    public ArrayList getQuestionN(int n){
        if(n > nQuestions) n = n%nQuestions; // just in case, although this shouldn't happen

        ArrayList<Word> words = new ArrayList<>();
        for (int i = 0; i < questionSize; i++){
            //words[i] = vocabularyArrayList.get(i);
            words.add(quiz[n][i]);
        }
        return words;
    }

    public ArrayList getNextQuestion(){
        return getQuestionN(counter++); // Postincrement, so starts at 0
    }

    public boolean finished(){
        return counter > nQuestions;
    }
}
