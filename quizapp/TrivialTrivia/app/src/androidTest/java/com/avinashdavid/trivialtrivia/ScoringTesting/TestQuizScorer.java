package com.avinashdavid.trivialtrivia.ScoringTesting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import android.util.Log;

import com.avinashdavid.trivialtrivia.Utility;
import com.avinashdavid.trivialtrivia.data.QuizDBContract;
import com.avinashdavid.trivialtrivia.data.QuizDBHelper;
import com.avinashdavid.trivialtrivia.questions.IndividualQuestion;
import com.avinashdavid.trivialtrivia.questions.QuestionsHandling;
import com.avinashdavid.trivialtrivia.scoring.QuestionScorer;
import com.avinashdavid.trivialtrivia.scoring.QuizScorer;

import java.util.ArrayList;

/**
 * Created by avinashdavid on 11/2/16.
 */

public class TestQuizScorer extends AndroidTestCase {
    private String LOG_TAG = TestQuizScorer.class.getSimpleName();
    QuizScorer mQuizScorer;
    Context mContext;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        mContext = context;
        QuizDBHelper.getInstance(mContext).getWritableDatabase().delete(QuizDBContract.QuizEntry.TABLE_NAME,null,null);
        QuizDBHelper.getInstance(mContext).getWritableDatabase().delete(QuizDBContract.CategoryEntry.TABLE_NAME,null,null);
        QuestionsHandling questionsHandling = QuestionsHandling.getInstance(mContext, 0);
        ArrayList<IndividualQuestion> allQuestions = questionsHandling.getFullQuestionSet();
        IndividualQuestion firstQuestion = allQuestions.get(0);
        IndividualQuestion secondQuestion = allQuestions.get(1);
        IndividualQuestion thirdQuestion = allQuestions.get(2);
        IndividualQuestion fourthQuestion = allQuestions.get(3);
        IndividualQuestion fifthQuestion = allQuestions.get(4);
        QuestionScorer firstQuestionScorer = new QuestionScorer(firstQuestion.questionNumber, firstQuestion.category, firstQuestion.correctAnswer, 0);
        firstQuestionScorer.setTimeTaken(4);
        QuestionScorer secondQuestionScorer = new QuestionScorer(secondQuestion.questionNumber, secondQuestion.category, 5, secondQuestion.correctAnswer, 3);
        QuestionScorer thirdQuestionScorer = new QuestionScorer(thirdQuestion.questionNumber, thirdQuestion.category, 7, thirdQuestion.correctAnswer, 2);
        QuestionScorer fourthQuestionScorer = new QuestionScorer(fourthQuestion.questionNumber, fourthQuestion.category, fourthQuestion.correctAnswer, 0);
        fourthQuestionScorer.setTimeTaken(3);
        QuestionScorer fifthQuestionScorer = new QuestionScorer(fifthQuestion.questionNumber, fifthQuestion.category, 10, fifthQuestion.correctAnswer, 3);
        mQuizScorer = QuizScorer.getInstance(mContext,5,0);
        mQuizScorer.addQuestionScorer(firstQuestionScorer);
        mQuizScorer.addQuestionScorer(secondQuestionScorer);
        mQuizScorer.addQuestionScorer(thirdQuestionScorer);
        mQuizScorer.addQuestionScorer(fourthQuestionScorer);
        mQuizScorer.addQuestionScorer(fifthQuestionScorer);
    }

    public void testQuizScore(){

        try {
            assertEquals("Quiz score of five-question quiz is four", 4, mQuizScorer.scoreQuiz(mQuizScorer.getQuestionScorers()));
        } catch (Exception e){
            Log.d(LOG_TAG, e.getMessage());
        }

        try {
            int howManyFromWorldCategory = mQuizScorer.getCategoryScoreReport().get(QuizScorer.SCORES_CORRECT_CATEGORY_ANSWERS)[IndividualQuestion.CATEGORY_WORLD];
            assertEquals("Quiz taker got two questions from 'world' category right",2,howManyFromWorldCategory);
        } catch (NullPointerException e){
            Log.d(LOG_TAG, e.getMessage());
        }

        try {
            int totalFromScienceCategory = mQuizScorer.getCategoryScoreReport().get(QuizScorer.SCORES_TOTAL_CATEGORY_QUESTIONS)[IndividualQuestion.CATEGORY_SCIENCE];
            assertEquals("There was 1 question with 'science' category",1,totalFromScienceCategory);
        } catch (NullPointerException e){
            Log.d(LOG_TAG, e.getMessage());
        }

        try {
            int totalFromWorldCategory = mQuizScorer.getCategoryScoreReport().get(QuizScorer.SCORES_TOTAL_CATEGORY_QUESTIONS)[IndividualQuestion.CATEGORY_WORLD];
            assertEquals("There were 2 questions with 'world' category",2,totalFromWorldCategory);
        } catch (NullPointerException e){
            Log.d(LOG_TAG, e.getMessage());
        }

        ArrayList<String> testScoreReport = mQuizScorer.getQuizCategoryScoreReportScoreStrings(mQuizScorer.getCategoryScoreReport());
        assertEquals("Quiz taker's score report shows '2/2' for 'world' category","2/2",testScoreReport.get(IndividualQuestion.CATEGORY_WORLD));
        assertEquals("Quiz taker's score report shows '0/1' for 'science' category","0/1",testScoreReport.get(IndividualQuestion.CATEGORY_SCIENCE));



        ArrayList<int[]> allAnswersTimes = mQuizScorer.getOverallTimeReport();
//        ArrayList<int[]> categoryAnswersTimes = mQuizScorer.getCategoryTotalTimeReport();
        assertEquals("Second element in wrong times list of getoveralltimereport() is 5",5,allAnswersTimes.get(QuizScorer.TIMES_WRONG_OVERALL)[1]);
        assertEquals("Quiz taker took an total of 14 seconds solving 'general' questions",14,mQuizScorer.getCategoryTotalTimeReport().get(QuizScorer.TIMES_OVERALL_BY_CATEGORY)[IndividualQuestion.CATEGORY_GENERAL]);
//        assertEquals("Total time taken for 'general' questions in quiz is 14 seconds",14,categoryAnswersTimes.get(QuizScorer.TIMES_CORRECT_BY_CATEGORY)[IndividualQuestion.CATEGORY_GENERAL]);

//        assertEquals("Average time on wrong answers is 5",5.0, Utility.getAverageFromIntListWITHNegativeValueElimination(allAnswersTimes.get(QuizScorer.TIMES_WRONG_OVERALL)));
//        assertEquals("Average time on correct answers is 6.0",6.0, Utility.getAverageFromIntListWITHNegativeValueElimination(allAnswersTimes.get(QuizScorer.TIMES_CORRECT_OVERALL)));
        assertEquals("Average time on correct answers is 6.0",6.0,mQuizScorer.getTimeAverageCorrect());
//        assertEquals("Average time on all answers is ", 5.80, Utility.getAverageFromCOMPLEMENTARYIntListsWITHNegativeValueElimination(allAnswersTimes.get(QuizScorer.TIMES_WRONG_OVERALL),allAnswersTimes.get(QuizScorer.TIMES_CORRECT_OVERALL)));
        assertEquals("Average time on all answers is ", 5.80, mQuizScorer.getTimeAverageAllQuestions());

        assertEquals("Average time on correctly answered 'general' questions is 7", 7.0, mQuizScorer.getCategoryAverageTimeReport().get(QuizScorer.TIMES_CORRECT_BY_CATEGORY)[IndividualQuestion.CATEGORY_GENERAL]);
        assertEquals("Average time on wrongly answered 'science' questions is 5", 5.0, mQuizScorer.getTimeAverageWRONGForCategory(IndividualQuestion.CATEGORY_SCIENCE));
        assertEquals("Average time on all answered 'science' questions is 5", 5.0, mQuizScorer.getTimeAverageOVERALLForCategory(IndividualQuestion.CATEGORY_SCIENCE));

    }

//    public void testQuizInsert(){
//        Log.d(LOG_TAG + "1", "testquizinsert" + Integer.toString(mQuizScorer.getSize()));
//        Uri uri = QuizScorer.createAndInsertQuizRecord(mContext, mQuizScorer);
//        Cursor c = mContext.getContentResolver().query(uri, new String[]{QuizDBContract.QuizEntry.COLUMN_NAME_AVERAGE_TIME_CORRECT}, null, null, null);
//        if (c.moveToFirst()){
//            assertEquals("Average time for correct answers is 6.0", 6.0, c.getDouble(0));
//        }
//
//    }

    public void testQuizContentValues(){
        try {
            ContentValues cv = QuizScorer.createQuizRecordContentValues(mContext, mQuizScorer);
            assertEquals("Average time on all questions is 5.80 sec", 5.80, cv.getAsDouble(QuizDBContract.QuizEntry.COLUMN_NAME_AVERAGE_TIME_OVERALL));
            assertEquals("Quiz size is 5", (int)5, (int)cv.getAsInteger(QuizDBContract.QuizEntry.COLUMN_NAME_QUIZ_SIZE));
            assertEquals("Quiz score is 4", (int)4, (int)cv.getAsInteger(QuizDBContract.QuizEntry.COLUMN_NAME_SCORE));
        } catch (Exception e){
            Log.d(LOG_TAG, e.getMessage());
        }
    }
//
//    public void testCategoryUpdate(){
//        int categoriesUpdated = QuizScorer.createAndUpdateCategoryRecords(mContext, mQuizScorer);
//        Cursor c = mContext.getContentResolver().query(QuizDBContract.CategoryEntry.CONTENT_URI, new String[]{QuizDBContract.CategoryEntry.COLUMN_NAME_TOTAL_QUESTIONS_ANSWERED, QuizDBContract.CategoryEntry.COLUMN_NAME_TOTAL_TIME_OVERALL}, null, null, null);
//        try {
//            assertEquals("6 records were updated",6, categoriesUpdated);
//            if (c.moveToFirst()) {
//                while (!c.isAfterLast()) {
//                    if (c.getPosition() == IndividualQuestion.CATEGORY_WORLD) {
//                        assertEquals("Total questions answered in world category is 2", 2, c.getInt(0));
//                    }
//                    c.moveToNext();
//                }
//            }
//        } finally {
//            c.close();
//        }
//    }
}
