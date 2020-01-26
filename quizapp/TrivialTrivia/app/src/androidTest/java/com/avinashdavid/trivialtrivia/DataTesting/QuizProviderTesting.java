package com.avinashdavid.trivialtrivia.DataTesting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import android.util.Log;

import com.avinashdavid.trivialtrivia.data.QuizDBContract;

import static com.avinashdavid.trivialtrivia.Utility.LOG_TAG;

/**
 * Created by avinashdavid on 11/7/16.
 */

public class QuizProviderTesting extends AndroidTestCase {
    private Context mContext;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        mContext = context;
    }

    public void testProviderInsert(){
        ContentValues quizValues = new ContentValues();
        Uri uri = null;
        Cursor c = null;
        quizValues.put(QuizDBContract.QuizEntry.COLUMN_NAME_QUIZ_SIZE,10);
        quizValues.put(QuizDBContract.QuizEntry.COLUMN_NAME_SCORE, 7);
        quizValues.put(QuizDBContract.QuizEntry.COLUMN_NAME_AVERAGE_TIME_OVERALL, 7.0);
        quizValues.put(QuizDBContract.QuizEntry.COLUMN_NAME_AVERAGE_TIME_CORRECT, 7.0);
        quizValues.put(QuizDBContract.QuizEntry.COLUMN_NAME_AVERAGE_TIME_WRONG, 7.0);
        try {
            uri = mContext.getContentResolver().insert(QuizDBContract.QuizEntry.CONTENT_URI, quizValues);
            if (uri==null){
                Log.d(LOG_TAG, "null uri at testProviderInsert()");
            }
        } finally {
            c = mContext.getContentResolver().query(uri, new String[]{QuizDBContract.QuizEntry.COLUMN_NAME_QUIZ_SIZE, QuizDBContract.QuizEntry.COLUMN_NAME_AVERAGE_TIME_CORRECT}, null,null,null);
            if (c.moveToFirst()) {
                assertEquals("Size of returnValue-th quiz is 10", 10, c.getInt(0));
                assertEquals("Average time on correct answers of returnValue-th quiz is 7.0", 7.0, c.getDouble(1));
            }
        }
    }

    public void testProviderUpdate(){
        ContentValues generalValues = new ContentValues();
        int updateRet = 0;
        Uri uri = null;
        generalValues.put(QuizDBContract.CategoryEntry.COLUMN_NAME_NAME, "general");
        generalValues.put(QuizDBContract.CategoryEntry.COLUMN_NAME_TOTAL_QUESTIONS_ANSWERED,10);
        generalValues.put(QuizDBContract.CategoryEntry.COLUMN_NAME_CORRECTLY_ANSWERED,7);
        generalValues.put(QuizDBContract.CategoryEntry.COLUMN_NAME_TOTAL_TIME_OVERALL,8.0);
        generalValues.put(QuizDBContract.CategoryEntry.COLUMN_NAME_TOTAL_TIME_CORRECT,8.0);
        generalValues.put(QuizDBContract.CategoryEntry.COLUMN_NAME_TOTAL_TIME_WRONG,8.0);
        try {
            uri = mContext.getContentResolver().insert(QuizDBContract.CategoryEntry.CONTENT_URI, generalValues);
            if (uri==null){
                Log.d(LOG_TAG, "null uri at testProviderInsert()");
            }
        } finally {
            ContentValues updateValues = new ContentValues();
            updateValues.put(QuizDBContract.CategoryEntry.COLUMN_NAME_TOTAL_QUESTIONS_ANSWERED,11);
            updateValues.put(QuizDBContract.CategoryEntry.COLUMN_NAME_CORRECTLY_ANSWERED,8);
            updateRet = mContext.getContentResolver().update(QuizDBContract.CategoryEntry.buildUriCategoryName("general"),updateValues,null,null);
            assertEquals("One record was updated",1,updateRet);
        }
    }
}
