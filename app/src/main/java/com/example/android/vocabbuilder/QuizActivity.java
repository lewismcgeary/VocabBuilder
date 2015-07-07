package com.example.android.vocabbuilder;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Stays Alive through the quiz, feeds Word[]s to QuestionFragment
 * Created by Lewis on 02/07/15.
 */
public class QuizActivity extends AppCompatActivity implements QuestionFragment.OnFragmentInteractionListener {
    static int questionCounter = 0;
    static ArrayList<Word> currentQuestionsAnswers;
    static int currentCorrectAnswer;
    // This can be upgraded to Soundpool.Builder in a few months
    @SuppressWarnings("deprecation")
    SoundPool quizSounds = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
    HashMap<String, Integer> soundMap = new HashMap<>();
    Vocabulary vocab;

    //Integer numberOfQuestionsLoaded;
    int totalQuestions = 7;
    int numberOfQuestionsLoaded=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) actionBar.hide();
        vocab = new Vocabulary(this);
        ArrayList<Word> AllAnswers = vocab.getVocabularyArrayList();

        quizSounds.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool quizSounds, int currentSound, int status) {

                displayProgress(0, (int)Math.floor(numberOfQuestionsLoaded*totalQuestions/13));
                numberOfQuestionsLoaded++;
                if (numberOfQuestionsLoaded==14){ // TODO : Once only sounds required by each quiz are loaded, change this to include variable
                    moveProgressBarToTop();
                    //nextFragment(questionCounter);
                }

            }
        });
        LoadSoundsTask loadSoundsAsynchronously = new LoadSoundsTask(this);
        loadSoundsAsynchronously.execute(AllAnswers);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.quiz_activity);
        LinearLayout progressBar = (LinearLayout) findViewById(R.id.progress_frame);
        progressBar.setY(0f);
        displayProgress(questionCounter,totalQuestions);
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
private class LoadSoundsTask extends AsyncTask<ArrayList<Word>, Void, HashMap>{

    private Context myCtx;

    public LoadSoundsTask(Context ctx){
        // set context
        this.myCtx = ctx;
    }
    @Override
    protected HashMap doInBackground(ArrayList<Word>... arrayList) {
        ArrayList<Word> AllAnswers =  arrayList[0];
        HashMap<String, Integer> asyncSoundMap = new HashMap<>();
        // load sound files for current vocabulary into the quizSounds pool
        // hashmap matches each word to the soundId
        for(int i=0; i<(AllAnswers.size()); i++){
            int currentSound = AllAnswers.get(i).audioRes(myCtx);
            String wordText = AllAnswers.get(i).getWordText();
            asyncSoundMap.put(wordText, quizSounds.load(myCtx, currentSound, 1));

        }
        asyncSoundMap.put("correctSound", quizSounds.load(myCtx, R.raw.yay, 1));
        asyncSoundMap.put("incorrectSound", quizSounds.load(myCtx, R.raw.click, 1));
        return asyncSoundMap;
    }


    @Override
    protected void onPostExecute(HashMap resultingHashmap) {
        super.onPostExecute(resultingHashmap);
        soundMap = resultingHashmap;

    }
}


    @Override
    public void replayPromptSound(View view){
        Random rn = new Random();
        float soundSpeed = (rn.nextInt(3)+9) / 10.0f;
        quizSounds.play(soundMap.get(currentQuestionsAnswers.get(currentCorrectAnswer).getWordText()), 1.0f, 1.0f, 1, 0, soundSpeed);
    }

    @Override
    public void onFragmentInteraction(View view) {
        questionCounter++;
        nextFragment(questionCounter);
    }


    @Override
    public void correctAnswerSelected(View view) {
        Random rn = new Random();
        float soundSpeed = (rn.nextInt(2)+8)/10.0f;
        quizSounds.play(soundMap.get("correctSound"), 1.0f, 1.0f, 1, 0, soundSpeed);
        getWindow().getDecorView().setBackgroundColor(Color.GREEN); // TODO: don't hardcode this
        questionCounter++;
        displayProgress(questionCounter,totalQuestions);
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
        int nAns = 3; // 3 for now, could be 2 or 4 or whatever
        Random rn = new Random();
        int correct = rn.nextInt(nAns);
        currentCorrectAnswer = correct;

        ArrayList<Word> Answers = new ArrayList<>(vocab.getn(nAns));
        currentQuestionsAnswers = Answers;

                quizSounds.play(soundMap.get(Answers.get(correct).getWordText()), 1.0f, 1.0f, 1, 0, 1.0f);


        if (questionNumber<totalQuestions){
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
    private void displayProgress(int full, int empty){
        // when loading, call like displayProgress(0,n), when playing displayProgress(n,total)
        // This will display a hard maximum number of stars defined by the number of kids in quiz_activity.xml
        // And a soft minimum defined by totalQuestions
        LinearLayout layout = (LinearLayout) findViewById(R.id.progress_frame);
        int count = layout.getChildCount();
        for(int i = count-1; i >= totalQuestions; i--){
            layout.removeViewAt(i);
        }
        ImageView v = null;
        for(int i = 0; i < totalQuestions; i++){
            try {
                v = (ImageView) layout.getChildAt(i);

            if(i < full) {
                    v.setImageResource(R.drawable.star_full);
                } else if(i < empty) {
                    v.setImageResource(R.drawable.star_empty);
                } else {
                    v.setImageResource(android.R.color.transparent);
                }
            } catch(NullPointerException e) {break;}
        }
    }
    private void moveProgressBarToTop() {
         LinearLayout layout = (LinearLayout) findViewById(R.id.progress_frame);
        /*Animation moveProgressBarToTop = AnimationUtils.loadAnimation(this, R.anim.move_progress_bar_to_top);
        moveProgressBarToTop.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                layout.setY(0f);
                nextFragment(questionCounter);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });*/
        ObjectAnimator moveProgressBarToTop = ObjectAnimator.ofFloat(layout, "Y", 0);
        moveProgressBarToTop.setDuration(300);
        moveProgressBarToTop.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                nextFragment(questionCounter);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        moveProgressBarToTop.start();
    }
}
