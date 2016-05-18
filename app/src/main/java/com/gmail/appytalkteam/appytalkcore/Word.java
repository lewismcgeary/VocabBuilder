package com.gmail.appytalkteam.appytalkcore;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lewis on 22/06/15.
 */
public class Word implements Parcelable{

    private String wordText;
    private int imageResourceId;
    private int audioResourceId;
    private String category="None";

    public Word(int imageResId, String word, int audioResId){
        imageResourceId =imageResId;
        wordText=word;
        audioResourceId =audioResId;
    }
    // overloading to get category in there
    public Word(int imageResId, String word, int audioResId, String Category){
        imageResourceId =imageResId;
        wordText=word;
        audioResourceId =audioResId;
        category = Category;
    }

    public int getImageResourceId(){
        return imageResourceId;
    }
    public int getAudioResourceId(){
        return audioResourceId;
    }
    public String getWordText(){
        return wordText;
    }
    public String getCategory() {
        return category;
    }

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

    private Word(Parcel in){
        imageResourceId = in.readInt();
        wordText = in.readString();
        audioResourceId = in.readInt();
        category = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageResourceId);
        dest.writeString(wordText);
        dest.writeInt(audioResourceId);
        dest.writeString(category);
    }

    @Override
    public int describeContents() {
        return 0;
    }


}
