package com.example.android.vocabbuilder;


import android.content.Context;
import android.content.Intent;
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

        // Swap so that current locale is at top of the list
        String currentlocale = Locale.getDefault().getLanguage();
        String locales[]= {"en", "ru", "es", "fr"};
        int j = 0;
        for(int i =0; i < locales.length; i++) {
            if(currentlocale.toLowerCase().startsWith(locales[i].toLowerCase())) j = i;
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
        // The following doesn't appear to change the Locale
        // See here for potential solution:
        // https://stackoverflow.com/questions/2264874/changing-locale-within-the-app-itself?rq=1
        String langRequested = view.getTag().toString();
        //Locale newloc = new Locale(langRequested,"");
        //Locale.setDefault(newloc);
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("language", langRequested);
        startActivity(intent);
    }
}