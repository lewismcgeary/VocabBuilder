package com.example.android.vocabbuilder;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by Lewis on 02/07/15.
 */
public class QuizActivityWithFragments extends AppCompatActivity implements QuestionFragment.OnFragmentInteractionListener {
    String displayMe = "initial";
    static int questionCounter = 0;
    static ArrayList<Word> currentQuestionsAnswers;
    static int currentCorrectAnswer;

    SoundPool quizSounds = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
    HashMap<String, Integer> soundMap = new HashMap<String, Integer>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity_with_fragments);
        loadSounds();
        nextFragment(questionCounter);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.quiz_activity_with_fragments);
        QuestionFragment nextQuestion = QuestionFragment.newInstance(currentQuestionsAnswers, currentCorrectAnswer);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.question_frame, nextQuestion);
        fragmentTransaction.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("Answers", currentQuestionsAnswers);
        outState.putInt("CorrectAnswer", currentCorrectAnswer);
    }

  /*  @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        setContentView(R.layout.quiz_activity_with_fragments);
        QuestionFragment nextQuestion = QuestionFragment.newInstance(savedInstanceState.getParcelableArrayList("Answers"), savedInstanceState.getInt("CorrectAnswer"));
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.question_frame, nextQuestion);
        fragmentTransaction.commit();

    }*/



    public void loadSounds(){
        Context context = this;
        Vocabulary vocab = new Vocabulary(this);
        int numberOfSounds = vocab.getWordCount();
        List<Word> AllAnswers = vocab.getVocabularyArrayList();
        for(int i=0; i<AllAnswers.size(); i++){
            soundMap.put(AllAnswers.get(i).getWordText(), quizSounds.load(context, AllAnswers.get(i).audioRes(context), 1));
        }
    soundMap.put("correctSound", quizSounds.load(context, R.raw.yay, 1));
        soundMap.put("incorrectSound", quizSounds.load(context, R.raw.click, 1));
    }

    @Override
    public void replayPromptSound(View view){
        Random rn = new Random();
        float soundSpeed = (rn.nextInt(3)+9)/10.0f;
        quizSounds.play(soundMap.get(currentQuestionsAnswers.get(currentCorrectAnswer).getWordText()), 1.0f, 1.0f, 1, 0, soundSpeed);
    }

    @Override
    public void onFragmentInteraction(View view) {
        questionCounter++;
        nextFragment(questionCounter);
    }

    @Override
    public void onAnswerSelected(View view) {

    }

    @Override
    public void correctAnswerSelected(View view) {
        Random rn = new Random();
        float soundSpeed = (rn.nextInt(2)+8)/10.0f;
        quizSounds.play(soundMap.get("correctSound"), 1.0f, 1.0f, 1, 0, soundSpeed);
        questionCounter++;
        Handler handler = new Handler(); // TODO: this delay is temporary to stop sounds overlapping
        handler.postDelayed(new Runnable() {
            public void run() {
                nextFragment(questionCounter);
            }
        }, 2000);
    }



    @Override
    public void wrongAnswerSelected(View view) {
        quizSounds.play(soundMap.get("incorrectSound"), 1.0f, 1.0f, 1, 0, 1.0f);
    }


    public void nextFragment(int questionNumber){
        Vocabulary vocab = new Vocabulary(this.getApplicationContext()); // cut , language.toUpperCase()
        //Context context = this.getApplicationContext();
        int nAns = 3; // 3 for now, could be 2 or 4 or whatever
        //int imRes[] = {R.id.button1, R.id.button2, R.id.button3}; // TODO: Get these programatically
        Random rn = new Random();
        int correct = rn.nextInt(nAns);
        currentCorrectAnswer = correct;

        ArrayList<Word> Answers = new ArrayList<>(vocab.getn(nAns)); // <Word> to <>
        currentQuestionsAnswers = Answers;
        quizSounds.play(soundMap.get(Answers.get(correct).getWordText()), 1.0f, 1.0f, 1, 0, 1.0f);
        //Word Answers[] = vocab.getn(nAns);
        //ArrayList<Word> answersArray = new ArrayList<Word>(Arrays.asList(Answers));
        if (questionNumber<10){
            QuestionFragment nextQuestion = QuestionFragment.newInstance(Answers, correct);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.question_frame, nextQuestion);
            fragmentTransaction.commit();}
        else{
            questionCounter = 0;
            quizSounds.release();
            this.finish();
            Intent intent = new Intent(this, LanguageSelectorActivity.class);
            startActivity(intent);
        }
    }
}
