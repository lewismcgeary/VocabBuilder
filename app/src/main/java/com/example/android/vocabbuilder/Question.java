package com.example.android.vocabbuilder;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

/**
 * This is our main question class. It gets words from Vocabulary, selects one to use as prompt
 * and checks if the player has selected it.
 */
public class Question extends AppCompatActivity{
    // TODO: A Question will have several possible Answers,
    // TODO: only one is the correct answer
    // TODO: A Question could have a method to select from Vocabulary
    // TODO: A Question could create several Answer Objects to use
    MediaPlayer correctSound;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        correctSound = MediaPlayer.create(this, R.raw.correct);
        TextView promptText = (TextView) findViewById(R.id.promptText);
        Vocabulary vocab = new Vocabulary(this.getApplicationContext());
        Context context = this.getApplicationContext();
        int nAns = 3; // 3 for now, could be 2 or 4 or whatever
        int imRes[] = {R.id.button1, R.id.button2, R.id.button3}; // TODO: Get these programatically
        Random rn = new Random();
        int correct = rn.nextInt(nAns);
        Word Answers[] = vocab.getn(nAns);
        promptText.setText(Answers[correct].getWordText());
        for (int i = 0; i < Answers.length; i++) {
            ImageButton butto = (ImageButton) findViewById(imRes[i]);
            butto.setImageResource(Answers[i].imageRes(context));
            if(i == correct) {butto.setTag("correct");} else {butto.setTag("wrong");}
        }



    }
    public void checkAnswer(View view) {
        // This is called when an answer is selected
        // answerSubmitted is the Tag of the selected button
        String answerSubmitted = view.getTag().toString();
        TextView feedbackText = (TextView) findViewById(R.id.feedbackText);
        // Checks if the correct answer was given or not
        // and gives corresponding result
        if (answerSubmitted.equals("correct")) {
            correctSound.start();
            feedbackText.setText("Correct");
        } else {
            feedbackText.setText("Wrong");
        }
    }





/*    Answer[correct].getAudio();
    Then do some display magic and wait for an answer*/
    }

