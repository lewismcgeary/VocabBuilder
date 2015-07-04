package com.example.android.vocabbuilder;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import java.util.Locale;

public class LanguageSelectorActivity extends AppCompatActivity implements OnTouchListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setButtons(getLocales());
    }
    // implement an onTouch() function, we don't care about the event
    // but it's more kid-friendly than onClick
    @Override
    public boolean onTouch(View view, MotionEvent event){
        switch(event.getAction()){
            case
            MotionEvent.ACTION_DOWN:
                String langRequested = view.getTag().toString();
                setLanguage(langRequested);

                startQuizActivity();

                return true;
        }
        return false;
    }

    public void setButtons(String locales[]){
        // Set the icons and tags for our buttons according to the locales[] Array
        // Also add an onTouchListener (see above) and start the Animation
        setContentView(R.layout.language_selector_activity);
        Context context;
        context = this.getApplicationContext();
        Animation zoomIn = AnimationUtils.loadAnimation(this, R.anim.language_select);

        int imRes[] = {R.id.button1, R.id.button2, R.id.button3, R.id.button4};
        for(int i =0; i < locales.length; i++) {
            ImageButton b = (ImageButton) findViewById(imRes[i]);
            b.setImageResource(context.getResources().getIdentifier(locales[i], "drawable", context.getPackageName()));
            b.setTag(locales[i]);
            b.setOnTouchListener(this);
            b.startAnimation(zoomIn);
        }
    }

    public String[] getLocales(){
// Return a an array of all our supported locales with system or (preferably) last used first
        SharedPreferences settings = getSharedPreferences("PrefsFile", MODE_PRIVATE);
        //noinspection
        String currentLocale = Locale.getDefault().getLanguage().substring(0, 2);
        String lang = settings.getString("language", currentLocale);

        // Swap so that last used or current locale is at top of the list
        String locales[] = {"en", "ru", "es", "fr"};
        int j = 0;
        for (int i = 0; i < locales.length; i++) {
            //noinspection ConstantConditions
            if (lang.equalsIgnoreCase(locales[i])) j = i;
        }
        String temp = locales[0];
        locales[0] = locales[j];
        locales[j] = temp;

        return locales;
    }

    public void setLanguage(String langRequested){
        SharedPreferences settings = getSharedPreferences("PrefsFile", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("language", langRequested);

        // Commit the edits!
        editor.commit();
    }

    public void startQuizActivity(){
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);

    }
}