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

    public void setWordText(String wordText) {
        this.wordText = wordText;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public void setAudioLocation(String audioLocation) {
        this.audioLocation = audioLocation;
    }

    //
    public Word(String image, String word, String audio){
        imageLocation = image;
        wordText = word;
        audioLocation = audio;


    }

    public int imageRes(Context context){

        return context.getResources().getIdentifier(imageLocation.replaceFirst("[.][^.]+$", ""), "drawable", context.getPackageName());
    }
    String getWordText(){
        return wordText;
    }

    String getImageLocation(){
        return imageLocation.replaceFirst("[.][^.]+$", "");
    }
    String getAudioLocation(){
        return audioLocation.replaceFirst("[.][^.]+$", "");
    }
}
