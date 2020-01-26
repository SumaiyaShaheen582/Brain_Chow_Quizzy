package com.avinashdavid.trivialtrivia.StatisticsTesting;

import android.content.ContentValues;
import android.content.Context;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.avinashdavid.trivialtrivia.data.QuizDBContract;
import com.avinashdavid.trivialtrivia.data.QuizDBHelper;
import com.avinashdavid.trivialtrivia.questions.IndividualQuestion;
import com.avinashdavid.trivialtrivia.statistics.CategoryStatisticsCalculator;

import java.util.ArrayList;

/**
 * Created by avinashdavid on 11/20/16.
 */

public class TestCategoryStatistics extends AndroidTestCase {
    Context mContext;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_2");
        mContext = context;
        QuizDBHelper.getInstance(mContext).getWritableDatabase().delete(QuizDBContract.QuizEntry.TABLE_NAME,null,null);
        QuizDBHelper.getInstance(mContext).getWritableDatabase().delete(QuizDBContract.CategoryEntry.TABLE_NAME,null,null);
        for (int i=0; i< IndividualQuestion.categoryList.size(); i++){
            ContentValues cv = new ContentValues();
            cv.put(QuizDBContract.CategoryEntry.COLUMN_NAME_NAME, IndividualQuestion.categoryList.get(i));
            cv.put(QuizDBContract.CategoryEntry.COLUMN_NAME_TOTAL_QUESTIONS_ANSWERED, 10);
            cv.put(QuizDBContract.CategoryEntry.COLUMN_NAME_TOTAL_TIME_OVERALL, 8);
            cv.put(QuizDBContract.CategoryEntry.COLUMN_NAME_TOTAL_TIME_CORRECT, 8);
            cv.put(QuizDBContract.CategoryEntry.COLUMN_NAME_TOTAL_TIME_WRONG, 8);
            if (i==0){
                cv.put(QuizDBContract.CategoryEntry.COLUMN_NAME_CORRECTLY_ANSWERED, 10);
            } else if (i==2){
                cv.put(QuizDBContract.CategoryEntry.COLUMN_NAME_CORRECTLY_ANSWERED, 6);
            } else {
                cv.put(QuizDBContract.CategoryEntry.COLUMN_NAME_CORRECTLY_ANSWERED, 8);
            }
            mContext.getContentResolver().insert(QuizDBContract.CategoryEntry.CONTENT_URI, cv);
        }
    }

    public void testCategoryStats(){
        ArrayList<Integer> categoryStatInts = CategoryStatisticsCalculator.getBestAndWorstCategoryPercentages(mContext);
        ArrayList<String> categoryStatStrings = CategoryStatisticsCalculator.getBestAndWorstCategoryStrings(mContext);
        assertEquals("Best category is general", "general", categoryStatStrings.get(CategoryStatisticsCalculator.BEST));
        assertEquals("Worst category is world", "world", categoryStatStrings.get(CategoryStatisticsCalculator.WORST));
        assertEquals("Best category percentage is 100",100, (int)categoryStatInts.get(CategoryStatisticsCalculator.BEST));
        assertEquals("Worst category percentage is 60", 60, (int)categoryStatInts.get(CategoryStatisticsCalculator.WORST));
    }
}
