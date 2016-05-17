package com.gmail.appytalkteam.appytalkcore;

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
    public int imageResourceId;
    public int audioResourceId;
    public String category="None";

    public void setWordText(String wordText) {
        this.wordText = wordText;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public void setAudioResourceId(int audioResourceId) {
        this.audioResourceId = audioResourceId;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    //
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageResourceId);
        dest.writeString(wordText);
        dest.writeInt(audioResourceId);
        dest.writeString(category);
    }

    // default constructor for no particular reason other than stopping errors
    public Word(){
        // empty!
    }

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

    private Word(Parcel in){
        imageResourceId = in.readInt();
        wordText = in.readString();
        audioResourceId = in.readInt();
        category = in.readString();
    }

    public int getImageResourceId(){
        return imageResourceId;
    }
    public int getAudioResourceId(){
        return audioResourceId;
    }
    String getWordText(){
        return wordText;
    }
    String getCategory() { return category;}


    @Override
    public int describeContents() {
        return 0;
    }


}
