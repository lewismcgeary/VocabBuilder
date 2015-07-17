package com.gmail.appytalkteam.appytalkcore;


import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionIntroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionIntroFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String CORRECT_ANSWER = "correctAnswer";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Word correctAnswer;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuestionIntroFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuestionIntroFragment newInstance(Word correctAnswer) {
        QuestionIntroFragment fragment = new QuestionIntroFragment();
        Bundle args = new Bundle();
        args.putParcelable(CORRECT_ANSWER, correctAnswer);
        fragment.setArguments(args);
        return fragment;
    }

    public QuestionIntroFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            correctAnswer = getArguments().getParcelable(CORRECT_ANSWER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Resources res = getResources();
        getActivity().getWindow().getDecorView().setBackgroundColor(res.getColor(R.color.backgroundcolor));
        View view = inflater.inflate(R.layout.fragment_question_intro, container, false);
        Button promptButton = (Button) view.findViewById(R.id.promptText);
        promptButton.setText(correctAnswer.getWordText());
        return view;
    }


}
