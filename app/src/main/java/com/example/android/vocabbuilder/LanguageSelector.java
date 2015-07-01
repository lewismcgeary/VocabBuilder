package com.example.android.vocabbuilder;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.android.vocabbuilder.R;

public class LanguageSelector extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language_selector);
        Context context;
        context = this.getApplicationContext();

        int imRes[] = {R.id.button1, R.id.button2, R.id.button3, R.id.button4};
        ImageButton b1 = (ImageButton) findViewById(imRes[1]);
        b1.setImageResource(context.getResources().getIdentifier("en.png", "drawable", context.getPackageName()));
        ImageButton b2 = (ImageButton) findViewById(imRes[2]);
        b2.setImageResource(context.getResources().getIdentifier("ru.png", "drawable", context.getPackageName()));
        ImageButton b3 = (ImageButton) findViewById(imRes[3]);
        b3.setImageResource(context.getResources().getIdentifier("es.png", "drawable", context.getPackageName()));
        ImageButton b4 = (ImageButton) findViewById(imRes[4]);
        b4.setImageResource(context.getResources().getIdentifier("fr.png", "drawable", context.getPackageName()));
    }
    public void setLanguage(View view){
        String langRequested = view.getTag().toString();
        Intent intent = new Intent(this, Question.class);
        startActivity(intent);
    }
}