package com.gmail.appytalkteam.appytalkcore;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
        }
        setContentView(R.layout.splash_screen);
        CreateDatabaseTask createDatabaseTask = new CreateDatabaseTask();
        createDatabaseTask.execute(this);
        // little pause for our splashscreen
        int TIMEOUT = 2500;
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                Resources res = getResources();
                String locale = res.getString(R.string.locale);
                // This method will be executed once the timer is over
                // Start your app main activity
                if(locale.equals("none")){
                    Intent intent = new Intent(MainActivity.this, LanguageSelectorActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                    // close this activity
                    finish();
                } else {
                    setLanguage(locale);
                    Intent intent = new Intent(MainActivity.this, CategorySelectorActivity.class);
                    startActivity(intent);
                }
            }
        }, TIMEOUT);

    }

    private class CreateDatabaseTask extends AsyncTask <Context, Void, Void>{

        @Override
        protected Void doInBackground(Context... context) {
            VocabularyDbHelperWithAssetHelper myDbHelper = new VocabularyDbHelperWithAssetHelper(context[0]);
             myDbHelper.createDataBase();

            return null;
        }
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
    public void setLanguage(String langRequested){
        SharedPreferences settings = getSharedPreferences("PrefsFile", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("language", langRequested);

        // Commit the edits!
        editor.commit();
    }

}
