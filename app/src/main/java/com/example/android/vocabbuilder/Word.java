package com.example.android.vocabbuilder;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lewis on 22/06/15.
 */
public class Word implements Parcelable{
    /**
     *Creates an object containing the text of a word and the locations of corresponding image &
     * audio files
    */
    public static final Parcelable.Creator<Word> CREATOR = new Parcelable.Creator<Word>() {

        public Word createFromParcel(Parcel in) {
            return new Word(in);
        }


        public Word[] newArray(int size) {
            return new Word[size];
        }
    };

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
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageLocation);
        dest.writeString(wordText);
        dest.writeString(audioLocation);
    }
    public Word(String image, String word, String audio){
        imageLocation=image;
        wordText=word;
        audioLocation=audio;
    }
    private Word(Parcel in){
        imageLocation = in.readString();
        wordText = in.readString();
        audioLocation = in.readString();



    }

    public int imageRes(Context context){

        return context.getResources().getIdentifier(imageLocation.replaceFirst("[.][^.]+$", ""), "drawable", context.getPackageName());
    }
    public int audioRes(Context context){

        return context.getResources().getIdentifier(audioLocation.replaceFirst("[.][^.]+$", ""), "raw", context.getPackageName());
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

    @Override
    public int describeContents() {
        return 0;
    }


}
