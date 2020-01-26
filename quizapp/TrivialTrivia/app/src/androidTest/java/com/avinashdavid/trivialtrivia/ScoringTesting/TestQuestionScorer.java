package com.avinashdavid.trivialtrivia.ScoringTesting;

import android.test.AndroidTestCase;

import com.avinashdavid.trivialtrivia.questions.IndividualQuestion;
import com.avinashdavid.trivialtrivia.questions.QuestionsHandling;
import com.avinashdavid.trivialtrivia.scoring.QuestionScorer;

import java.util.ArrayList;

/**
 * Created by avinashdavid on 11/2/16.
 */

public class TestQuestionScorer extends AndroidTestCase {
    public void testQuestionScorer(){
        QuestionsHandling questionsHandling = QuestionsHandling.getInstance(getContext(), 10);
        ArrayList<IndividualQuestion> allQuestions = questionsHandling.getFullQuestionSet();
        IndividualQuestion fifthQuestion = allQuestions.get(4);
        QuestionScorer questionScorer = new QuestionScorer(fifthQuestion.questionNumber, fifthQuestion.category,fifthQuestion.correctAnswer,2);
        assertEquals(false,questionScorer.getQuestionEvaluation());
    }
}
