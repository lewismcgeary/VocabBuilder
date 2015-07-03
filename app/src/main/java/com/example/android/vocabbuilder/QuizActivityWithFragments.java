package com.example.android.vocabbuilder;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Lewis on 02/07/15.
 */
public class QuizActivityWithFragments extends AppCompatActivity implements QuestionFragment.OnFragmentInteractionListener {
    String displayMe = "initial";
    static int questionCounter = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity_with_fragments);
        nextFragment(questionCounter);
    }

    @Override
    public void onFragmentInteraction(View view) {
        questionCounter++;
        nextFragment(questionCounter);
    }

    public void nextFragment(int questionCounter){
        Vocabulary vocab = new Vocabulary(this.getApplicationContext()); // cut , language.toUpperCase()
        //Context context = this.getApplicationContext();
        int nAns = 3; // 3 for now, could be 2 or 4 or whatever
        //int imRes[] = {R.id.button1, R.id.button2, R.id.button3}; // TODO: Get these programatically
        Random rn = new Random();
        int correct = rn.nextInt(nAns);


        ArrayList<Word> Answers = new ArrayList<>(vocab.getn(nAns)); // <Word> to <>
        //Word Answers[] = vocab.getn(nAns);
        //ArrayList<Word> answersArray = new ArrayList<Word>(Arrays.asList(Answers));
        if (questionCounter<10){
            questionCounter++;
            QuestionFragment nextQuestion = QuestionFragment.newInstance(Answers, correct);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.question_frame, nextQuestion);
            fragmentTransaction.commit();}
        else{
            questionCounter = 0;
            Intent intent = new Intent(this, LanguageSelectorActivity.class);
            startActivity(intent);
        }
    }
}
