package com.example.android.vocabbuilder;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;

import java.io.IOException;
import java.util.Random;


/**
 * Created by Lewis on 23/06/15.
 */
public class Question extends AppCompatActivity{
    // TODO: A Question will have several possible Answers,
    // TODO: only one is the correct answer
    // TODO: A Question could have a method to select from Vocabulary
    // TODO: A Question could create several Answer Objects to use
    // TODO:
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Vocabulary vocab = new Vocabulary();
        Context context = this.getApplicationContext();
        int nAns = 3; // 3 for now, could be 2 or 4 or whatever
        int imRes[] = {R.id.button1, R.id.button2, R.id.button3}; // TODO: Get these programatically
        Random rn = new Random();
        int correct = rn.nextInt(nAns);

        Word Answers[] = vocab.getn(nAns);

        for (int i = 0; i < Answers.length; i++)
        {
        ImageButton butto = (ImageButton) findViewById(imRes[i]);
        butto.setImageResource(Answers[i].imageRes(context));
        }

       VocabularyDbHelper myDbHelper = new VocabularyDbHelper(this);


        try {

            myDbHelper.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }



            myDbHelper.openDataBase();


        }


/*    Answer[correct].getAudio();
    Then do some display magic and wait for an answer*/
    }

