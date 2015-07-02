package com.example.android.vocabbuilder;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import java.util.Locale;

public class LanguageSelectorActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language_selector_activity);
        Context context;
        context = this.getApplicationContext();
        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        String currentLocale = Locale.getDefault().getLanguage().substring(0,2);
        String lang = settings.getString("language", currentLocale);
        // Swap so that last used or current locale is at top of the list
        String locales[]= {"en", "ru", "es", "fr"};
        int j = 0;
        for(int i =0; i < locales.length; i++) {
            if(lang.equalsIgnoreCase(locales[i])) j = i;
        }
        String temp = locales[0];
        locales[0] = locales[j];
        locales[j] = temp;

        int imRes[] = {R.id.button1, R.id.button2, R.id.button3, R.id.button4};
        for(int i =0; i < locales.length; i++) {
            ImageButton b = (ImageButton) findViewById(imRes[i]);
            b.setImageResource(context.getResources().getIdentifier(locales[i], "drawable", context.getPackageName()));
            b.setTag(locales[i]);
        }
    }
    public void setLanguage(View view){
        // Get the language from the button tag
        String langRequested = view.getTag().toString();

        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("language", langRequested);
        // Commit the edits!
        editor.commit();

        //Locale newloc = new Locale(langRequested,"");
        //Locale.setDefault(newloc);
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("language", langRequested);
        startActivity(intent);
    }
}