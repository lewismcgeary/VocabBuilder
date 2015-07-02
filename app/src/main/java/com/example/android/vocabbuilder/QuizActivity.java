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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String language = getIntent().getStringExtra("language");
        Vocabulary vocab = new Vocabulary(this.getApplicationContext(), language.toUpperCase());
        //Context context = this.getApplicationContext();
        int nAns = 3; // 3 for now, could be 2 or 4 or whatever
        //int imRes[] = {R.id.button1, R.id.button2, R.id.button3}; // TODO: Get these programatically
        Random rn = new Random();
        int correct = rn.nextInt(nAns);


        ArrayList<Word> Answers = new ArrayList<Word>(vocab.getn(nAns));
        //Word Answers[] = vocab.getn(nAns);
        //ArrayList<Word> answersArray = new ArrayList<Word>(Arrays.asList(Answers));

        Intent intent = new Intent(this, Question.class);
        intent.putParcelableArrayListExtra("Answers", Answers);
        intent.putExtra("correct", correct);
        startActivity(intent);
    }
}
