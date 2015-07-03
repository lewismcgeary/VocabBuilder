package com.example.android.vocabbuilder;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import java.util.Locale;

public class LanguageSelectorActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language_selector_activity);
        Context context;
        context = this.getApplicationContext();
        Animation zoomIn = AnimationUtils.loadAnimation(this, R.anim.language_select);


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
        int imRes[] = {R.id.button1, R.id.button2, R.id.button3, R.id.button4};
        for(int i =0; i < locales.length; i++) {
            ImageButton b = (ImageButton) findViewById(imRes[i]);
            b.setImageResource(context.getResources().getIdentifier(locales[i], "drawable", context.getPackageName()));
            b.setTag(locales[i]);
            b.startAnimation(zoomIn);
        }
    }
    public void setLanguage(View view){
        String langRequested = view.getTag().toString();

        /* This was weird and glitchy, so it's commented out
         int imRes[] = {R.id.button1, R.id.button2, R.id.button3, R.id.button4};
        Context context = this.getApplicationContext();
        // Get the language from the button tag
        // Start animation
        Animation zoomOut = AnimationUtils.loadAnimation(this, R.anim.language_selected);
        for(int i =0; i < imRes.length; i++) {
            ImageButton b = (ImageButton) findViewById(imRes[i]);
            b.startAnimation(zoomOut);
        }
        */
        // and do some work!
        SharedPreferences settings = getSharedPreferences("PrefsFile", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("language", langRequested);
        // Commit the edits!
        editor.commit();

        //Locale newloc = new Locale(langRequested,"");
        //Locale.setDefault(newloc);
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }
}