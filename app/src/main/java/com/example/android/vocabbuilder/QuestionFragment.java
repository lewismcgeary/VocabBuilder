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
    private static final String ANSWERS_ARRAY = "answersArray";
    private static final String CORRECT_INT = "correct";

    // TODO: Rename and change types of parameters
    private ArrayList<Word> Answers;
    private int correct;

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


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().getWindow().getDecorView().setBackgroundColor(0xFFCCFFCC); // TODO: don't hardcode this
        View view =  inflater.inflate(R.layout.question_fragment, container, false);
        Context context = getActivity();
        Button promptText = (Button) view.findViewById(R.id.promptText);
        int imRes[] = {R.id.button1, R.id.button2, R.id.button3}; // TODO: Get these programatically
        promptText.setText(Answers.get(correct).getWordText());
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
            if(i == correct) {butto.setTag("correct");} else {butto.setTag("wrong");}
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

    // TODO: Rename method, update argument and hook method into UI event


    public void onButtonPressed(View view) {
           if (mListener != null) {
               mListener.onFragmentInteraction(view);
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
        void correctAnswerSelected(View view);
        void wrongAnswerSelected(View view);
        void replayPromptSound(View view);
    }

}
