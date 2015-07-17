package com.gmail.appytalkteam.appytalkcore;

/**
 * This represents a word as learned by our user, with variables for how well she knows it
 * Created by derwin on 17/07/15.
 */
public class UserWord extends Word {
    public int seen; // this should be boolean really, but sqlite doesn't do boolean
    public int score;
    public int box; // one to five
    public double probability; // == 1/box but then normalised

    public UserWord(String wordtext, String imagelocation, String audiolocation, String Category){
        score = 0;
        box = 1;
        probability = 0.1;
        wordText = wordtext;
        imageLocation = imagelocation;
        audioLocation = audiolocation;
        category = Category;
    }

    public UserWord(String wordtext, String imagelocation, String audiolocation, String Category, int Score, int Box, int Seen){
        score = Score;
        box = Box;
        seen = Seen;
        probability = 0.1;
        wordText = wordtext;
        imageLocation = imagelocation;
        audioLocation = audiolocation;
        category = Category;
    }

    public void setResult(int i){
        score = score+i;
        if(score>5 & box < 5) box++;
        if(score<-1 & box > 1) box--;
        // if box = 5 or box = 1, we just... stay there, I guess
    }
    public UserWord Word2User(Word wrd){
        UserWord uwrd = new UserWord(wrd.wordText,wrd.imageLocation,wrd.audioLocation,wrd.category);
        return uwrd;
    }
    public Word asWord(){
        Word wrd = new Word(this.imageLocation,this.wordText,this.audioLocation,this.category);
        return wrd;
    }
}
