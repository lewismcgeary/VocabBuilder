package com.gmail.appytalkteam.appytalkcore;

/**
 * This is supposed to implement semi-intelligent spaced repetition and dole out
 * words the user doesn't know so well
 * Created by derwin on 17/07/15.
 */

// this class incorporates how well we know the word plus
// The probability (which depends on how well we know it)
public class Spacer {
/**
    private class UserWord extends Word {
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

        public void setResult(int i){
            score = score+i;
            if(score>5 & box < 5) box++;
            if(score<-1 & box > 1) box--;
            // if box = 5 or box = 1, we just... stay there, I guess
        }
    }

    ArrayList<UserWord> uservocab;
    ArrayList<Word> remainingvocab;

    public Spacer(Context context) {
        uservocab = readUserVocab(context);
        remainingvocab = readRemainingVocab(context);
    }

    public void questionResult(Word word, int tries) {
        UserWord curword = findUserWord(word);
        curword.setResult(2-tries); // if three tries, -1, two tries 0, one try 1
        if(tries == 1) getNewWord();
        renormaliseProbabilities();
        syncDB();
    }

    public UserWord findUserWord(Word wordRequested){
        // iterate through uservocab until the imageLocation matches
        for(UserWord word: uservocab){
            if(word.imageLocation.equals(wordRequested.imageLocation)) return word;
        }
    }

    public void renormaliseProbabilities(){
        double total = 0.0;
        for(UserWord word: uservocab){
            word.probability = 1/word.box;
            total += word.probability;
        }
        for(UserWord word: uservocab){
            word.probability = word.probability/total;
        }
    }

    private ArrayList<UserWord> readUserVocab(Context context) {
        // get the words we've already learned from the database
        if (file.exists("userwords.sqlite")) {

        } else {
            // if there is no database, create it here
            return new ArrayList<UserWord>(); // empty!
        }
    }

    private ArrayList<Word> readRemainingVocab(Context context){
        // get the words we haven't yet learned from the database
        ArrayList<Word> rvocab = new ArrayList<Word>();
        if(file.exists("userwords.sqlite")){
            // get words from there
        } else {
            VocabularyDbHelper myDbHelper = new VocabularyDbHelper(context);
            SharedPreferences settings = context.getSharedPreferences("PrefsFile", Context.MODE_PRIVATE);
            String language = settings.getString("language", "en"); // Should never have to default, but en is OK if we do
            String category = settings.getString("category", "colours"); // Should never have to default, but colours is OK if we do
            //noinspection
            rvocab = myDbHelper.getWordsFromDataBase(language.toUpperCase(), category);
        }
        return rvocab;
    }

    public void getNewWord(){
        uservocab.add(new UserWord(remainingvocab.get(0)));
        remainingvocab.remove(0);
    }

    public void syncDB(){
        // write the words we know and the words we don't to userwords.sqlite
    }
 */
}
