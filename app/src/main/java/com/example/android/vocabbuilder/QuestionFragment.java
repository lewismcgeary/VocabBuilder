package com.example.android.vocabbuilder;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

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
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String QUESTION_COUNT = "questionCount";
    private static final String ANSWERS_ARRAY = "answersArray";
    private static final String CORRECT_INT = "correct";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String displayText;
    private ArrayList<Word> Answers;
    private int correct;
    private int questionCount;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuestionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuestionFragment newInstance(ArrayList answers, int correct) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ANSWERS_ARRAY, answers);
        args.putInt(CORRECT_INT, correct);
       // args.putInt(QUESTION_COUNT, questionCounter);
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
            questionCount = getArguments().getInt(QUESTION_COUNT);
            correct = getArguments().getInt(CORRECT_INT);
            Answers = getArguments().getParcelableArrayList(ANSWERS_ARRAY);


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.question_activity, container, false);
        Context context = getActivity();
        Button promptText = (Button) view.findViewById(R.id.promptText);
        //Vocabulary vocab = new Vocabulary(this.getApplicationContext(), language.toUpperCase());

        //int nAns = 3; // 3 for now, could be 2 or 4 or whatever
        int imRes[] = {R.id.button1, R.id.button2, R.id.button3}; // TODO: Get these programatically
        //Random rn = new Random();
        //int correct = rn.nextInt(nAns);
        //Word Answers[] = vocab.getn(nAns);
        String correctAnswer = Answers.get(correct).getWordText();
        promptText.setText(Answers.get(correct).getWordText());
        for (int i = 0; i < Answers.size(); i++) {
            ImageButton butto = (ImageButton) view.findViewById(imRes[i]);
            butto.setImageResource(Answers.get(i).imageRes(context));
            butto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onButtonPressed(view);
                }
            });
            if(i == correct) {butto.setTag("correct");} else {butto.setTag("wrong");}
        }


        promptText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        replayPromptSound(view);
                    }
                });
        return view;


    }
    public void replayPromptSound(View view){

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    // TODO: Rename method, update argument and hook method into UI event


    public void onButtonPressed(View view) {
       if(view.getTag()=="correct") {
           if (mListener != null) {
               mListener.onFragmentInteraction(view);
           }
       }
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
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name

        void onFragmentInteraction(View view);
    }
}
