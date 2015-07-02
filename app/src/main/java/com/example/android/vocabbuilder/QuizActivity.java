package com.example.android.vocabbuilder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

/**
 * This sets up a new vocabulary, samples words, and launches QuestionActivity
 */
public class QuizActivity extends AppCompatActivity {
    static int questionCount;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences settings = getSharedPreferences("PrefsFile", MODE_PRIVATE);
        String language = settings.getString("language", "en"); // Should never have to default, but en is OK if we do
        Vocabulary vocab = new Vocabulary(this.getApplicationContext(), language.toUpperCase());
        //Context context = this.getApplicationContext();
        int nAns = 3; // 3 for now, could be 2 or 4 or whatever
        //int imRes[] = {R.id.button1, R.id.button2, R.id.button3}; // TODO: Get these programatically
        Random rn = new Random();
        int correct = rn.nextInt(nAns);


        ArrayList<Word> Answers = new ArrayList<>(vocab.getn(nAns)); // <Word> to <>
        //Word Answers[] = vocab.getn(nAns);
        //ArrayList<Word> answersArray = new ArrayList<Word>(Arrays.asList(Answers));
        if (questionCount<10){
            questionCount++;
        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putParcelableArrayListExtra("Answers", Answers);
        intent.putExtra("correct", correct);
        startActivity(intent);}
        else{
            questionCount = 0;
            Intent intent = new Intent(this, LanguageSelectorActivity.class);
            startActivity(intent);
        }
    }
}
