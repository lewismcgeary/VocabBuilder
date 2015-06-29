package com.example.android.vocabbuilder;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    MediaPlayer correctSound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = this.getApplicationContext();
        // Fetch the feedback audio for use when a correct answer is given
        correctSound = MediaPlayer.create(this, R.raw.correct);
        Vocabulary vocab = new Vocabulary();
        Word[] words = vocab.getn(3);
        ImageButton button1 = (ImageButton) findViewById(R.id.button1);
        button1.setImageResource(words[0].imageRes(context));//(R.drawable.sample_cat);
        ImageButton button2 = (ImageButton) findViewById(R.id.button2);
        button2.setImageResource(words[1].imageRes(context));//(R.drawable.sample_car);
        ImageButton button3 = (ImageButton) findViewById(R.id.button3);
        button3.setImageResource(words[2].imageRes(context));//(R.drawable.sample_dog);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void checkAnswer(View view) {
        // This is called when an answer is selected
        // answerSubmitted is the Tag of the selected button
        String answerSubmitted = view.getTag().toString();
        TextView feedbackText = (TextView) findViewById(R.id.feedbackText);
        // Checks if the correct answer was given or not
        // and gives corresponding result
        // TODO: update if statement so answer is not hard-coded
        if (answerSubmitted.equals("car")) {
            correctSound.start();
            feedbackText.setText("Correct");
        } else {
            feedbackText.setText("Wrong");
        }
    }
}
