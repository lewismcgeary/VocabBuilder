package com.example.android.vocabbuilder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Lewis on 01/07/15.
 */
public class QuizActivity extends AppCompatActivity {
    static String language;
    static int questionCount;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (language == null){
        language = getIntent().getStringExtra("language");}
        Vocabulary vocab = new Vocabulary(this.getApplicationContext(), language.toUpperCase());
        //Context context = this.getApplicationContext();
        int nAns = 3; // 3 for now, could be 2 or 4 or whatever
        //int imRes[] = {R.id.button1, R.id.button2, R.id.button3}; // TODO: Get these programatically
        Random rn = new Random();
        int correct = rn.nextInt(nAns);


        ArrayList<Word> Answers = new ArrayList<Word>(vocab.getn(nAns));
        //Word Answers[] = vocab.getn(nAns);
        //ArrayList<Word> answersArray = new ArrayList<Word>(Arrays.asList(Answers));
        if (questionCount<10){
            questionCount++;
        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putParcelableArrayListExtra("Answers", Answers);
        intent.putExtra("correct", correct);
        startActivity(intent);}
        else{
            language = null;
            questionCount = 0;
            Intent intent = new Intent(this, LanguageSelectorActivity.class);
            startActivity(intent);
        }
    }
}
