package com.avinashdavid.trivialtrivia.UI;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.avinashdavid.trivialtrivia.R;
import com.avinashdavid.trivialtrivia.questions.QuestionsHandling;

import java.util.ArrayList;

/**
 * Created by avinashdavid on 11/27/16.
 */

@TargetApi(13)
public class FragmentQuestionHoneycomb extends android.app.Fragment {
    private ArrayList<String> mCurrentDisplayQuestion;

    private TextView mQuestionView;
    private TextView mChoice1TextView;
    private TextView mChoice2TextView;
    private TextView mChoice3TextView;
    private TextView mChoice4TextView;

    public static android.app.Fragment getInstance(ArrayList<String> currentQuestion){
        FragmentQuestionHoneycomb fragmentQuestion = new FragmentQuestionHoneycomb();
        fragmentQuestion.mCurrentDisplayQuestion = currentQuestion;
        return fragmentQuestion;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_question, container, false);

        mQuestionView = (TextView)rootview.findViewById(R.id.question_textview);
        mChoice1TextView = (TextView)rootview.findViewById(R.id.choice1);
        mChoice2TextView = (TextView)rootview.findViewById(R.id.choice2);
        mChoice3TextView = (TextView)rootview.findViewById(R.id.choice3);
        mChoice4TextView = (TextView)rootview.findViewById(R.id.choice4);
        setAndUpdateTextViews();

        return rootview;
    }

    private void setAndUpdateTextViews(){
        mQuestionView.setText(mCurrentDisplayQuestion.get(QuestionsHandling.INDEX_QUESTION));
        mChoice1TextView.setText(mCurrentDisplayQuestion.get(QuestionsHandling.INDEX_CHOICE_1));
        mChoice2TextView.setText(mCurrentDisplayQuestion.get(QuestionsHandling.INDEX_CHOICE_2));
        mChoice3TextView.setText(mCurrentDisplayQuestion.get(QuestionsHandling.INDEX_CHOICE_3));
        mChoice4TextView.setText(mCurrentDisplayQuestion.get(QuestionsHandling.INDEX_CHOICE_4));
    }
}
