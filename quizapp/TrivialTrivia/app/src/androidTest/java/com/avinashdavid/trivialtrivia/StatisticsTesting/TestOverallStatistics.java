package com.avinashdavid.trivialtrivia.StatisticsTesting;

import android.content.ContentValues;
import android.content.Context;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.avinashdavid.trivialtrivia.data.QuizDBContract;
import com.avinashdavid.trivialtrivia.data.QuizDBHelper;
import com.avinashdavid.trivialtrivia.statistics.OverallStatisticsCalculator;

import java.util.ArrayList;

/**
 * Created by avinashdavid on 11/14/16.
 */

public class TestOverallStatistics extends AndroidTestCase {
    Context mContext;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_1");
        mContext = context;
        QuizDBHelper.getInstance(mContext).getWritableDatabase().delete(QuizDBContract.QuizEntry.TABLE_NAME,null,null);
        QuizDBHelper.getInstance(mContext).getWritableDatabase().delete(QuizDBContract.CategoryEntry.TABLE_NAME,null,null);
        ContentValues cv = new ContentValues();
        cv.put(QuizDBContract.QuizEntry.COLUMN_NAME_QUIZ_SIZE, 10);
        cv.put(QuizDBContract.QuizEntry.COLUMN_NAME_SCORE, 10);
        cv.put(QuizDBContract.QuizEntry.COLUMN_NAME_AVERAGE_TIME_OVERALL, 7);
        cv.put(QuizDBContract.QuizEntry.COLUMN_NAME_AVERAGE_TIME_WRONG, 7);
        cv.put(QuizDBContract.QuizEntry.COLUMN_NAME_AVERAGE_TIME_CORRECT, 7);
        mContext.getContentResolver().insert(QuizDBContract.QuizEntry.CONTENT_URI, cv);
        cv = new ContentValues();
        cv.put(QuizDBContract.QuizEntry.COLUMN_NAME_QUIZ_SIZE, 10);
        cv.put(QuizDBContract.QuizEntry.COLUMN_NAME_SCORE, 8);
        cv.put(QuizDBContract.QuizEntry.COLUMN_NAME_AVERAGE_TIME_OVERALL, 5);
        cv.put(QuizDBContract.QuizEntry.COLUMN_NAME_AVERAGE_TIME_WRONG, 5);
        cv.put(QuizDBContract.QuizEntry.COLUMN_NAME_AVERAGE_TIME_CORRECT, 3);
        mContext.getContentResolver().insert(QuizDBContract.QuizEntry.CONTENT_URI, cv);
    }

    public void testOverallStatsCalculator(){
        ArrayList<Double> overallStats = OverallStatisticsCalculator.getOverallPerformanceAndAverages(mContext);
        assertEquals("Total number of quizzes is 2",2.0,overallStats.get(OverallStatisticsCalculator.TOTAL_NUMBER_QUIZZES_TAKEN));
        assertEquals("Highest score is 10", 10.0, overallStats.get(OverallStatisticsCalculator.HIGHEST_SCORE));
        assertEquals("Highest score occurred once", 1.0, overallStats.get(OverallStatisticsCalculator.HIGHEST_SCORE_OCCURRENCE));
        assertEquals("There was one perfect quiz",1.0, overallStats.get(OverallStatisticsCalculator.PERFECT_QUIZZES));
        assertEquals("Average time on correct answers was 4", 5.0, overallStats.get(OverallStatisticsCalculator.AVERAGE_TIME_CORRECT_ANSWERS));
        assertEquals("Average time on wrong answers was 6", 6.0, overallStats.get(OverallStatisticsCalculator.AVERAGE_TIME_WRONG_ANSWERS));
        assertEquals("Average percentage score was 90", 90.0, overallStats.get(OverallStatisticsCalculator.AVERAGE_PERCENTAGE_SCORE));
    }
}
