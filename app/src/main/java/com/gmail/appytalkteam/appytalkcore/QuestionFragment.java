package com.gmail.appytalkteam.appytalkcore;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuestionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment {
    // the fragment initialization parameters
    private static final String ANSWERS_ARRAY = "answersArray";
    private static final String CORRECT_INT = "correct";
    private static final String TRIED_BOOL = "tried";
    private ArrayList<Word> Answers;
    private int correct;
    private boolean[] tried;
    private OnFragmentInteractionListener mListener;

    /**
     * Displays a question consisting of a Button showing a word and a number of Image Buttons.
     * If the Image Button which matches the word is pressed then the Question is shown as correctly
     * answered and a new one displayed. If a wrong answer is selected then that option is disabled
     * and the user can try again until the correct answer is given.
     * The text button can be pressed to play an audio file of the word.
     *
     * @param theQ the current Question
     * @return A new instance of fragment QuestionFragment.
     */

    public static QuestionFragment newInstance(Question theQ) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ANSWERS_ARRAY, theQ.getWords());
        args.putInt(CORRECT_INT, theQ.getAnswer());
        args.putBooleanArray(TRIED_BOOL,theQ.guessed());
        fragment.setArguments(args);
        return fragment;
    }

    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            correct = getArguments().getInt(CORRECT_INT);
            Answers = getArguments().getParcelableArrayList(ANSWERS_ARRAY);
            tried = getArguments().getBooleanArray(TRIED_BOOL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Resources res = getResources();
        getActivity().getWindow().getDecorView().setBackgroundColor(res.getColor(R.color.backgroundcolor));
        View view =  inflater.inflate(R.layout.question_fragment, container, false);
        Context context = getActivity();
        Button promptText = (Button) view.findViewById(R.id.promptText);
        int imRes[] = {R.id.button1, R.id.button2, R.id.button3}; // TODO: Get these programatically
        // set button to display the required word
        promptText.setText(Answers.get(correct).getWordText());
        // Set up the answer buttons with appropriate images and tag correct answer with "correct"
        for (int i = 0; i < Answers.size(); i++) {
            ImageButton butto = (ImageButton) view.findViewById(imRes[i]);
            butto.setImageResource(Answers.get(i).imageRes(context));
            butto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(view.getTag()=="correct") {
                        mListener.correctAnswerSelected(view);
                    }else{
                        mListener.wrongAnswerSelected(view);
                    }
                }
            });
            // If fragment has been restarted, previously tried wrong answers will be faded and
            // button will be disabled
            if(tried[i] && i != correct) {
                butto.setClickable(false);
                butto.setAlpha(0.3f);
            }
            if(i == correct) {butto.setTag("correct");} else {butto.setTag(Integer.toString(i));} // eg. if answer 2 is correct,tags  "0", "correct", "2"
        }


        promptText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.replayPromptSound(view);
            }
        });
        return view;


    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface is implemented by QuizActivity to allow communication to be received from this
     * fragment
     */
    public interface OnFragmentInteractionListener {
        void correctAnswerSelected(View view);
        void wrongAnswerSelected(View view);
        void replayPromptSound(View view);
    }

}
