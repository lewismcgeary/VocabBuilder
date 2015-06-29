package com.example.android.vocabbuilder;

import android.content.Context;

/**
 * Created by Lewis on 22/06/15.
 */
public class Word {
    // TODO: The Word class will need to have an associated String version,
    // TODO: audio version and image file.
    // TODO: The paths will come from R.raw.vocabulary.sqlite
    // TODO:
    public String wordText;
    public String imageLocation;
    public String audioLocation;
    //
    public Word(String word, String image, String audio){
        wordText = word;
        imageLocation = image;
        audioLocation = audio;

    }

    public int imageRes(Context context){

        return context.getResources().getIdentifier(imageLocation, "drawable", context.getPackageName());
    }
    String getWordText(){
        return wordText;
    }

    String getImageLocation(){
        return imageLocation;
    }
    String getAudioLocation(){
        return audioLocation;
    }
}
