package com.avinashdavid.trivialtrivia.DataTesting;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import android.util.Log;

import com.avinashdavid.trivialtrivia.data.QuizDBContract;
import com.avinashdavid.trivialtrivia.data.QuizDBHelper;

/**
 * Created by avinashdavid on 11/7/16.
 */

public class QuizDBHelperTesting extends AndroidTestCase {
    QuizDBHelper mQuizDBHelper;
    private static final String LOG_TAG = "QuizDBHelperTesting";
    @Override
    public void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        mQuizDBHelper = QuizDBHelper.getInstance(context);
    }
//    public void testTableCreation(){
//        SQLiteDatabase db = mQuizDBHelper.getReadableDatabase();
//        Cursor c = db.rawQuery("SELECT name FROM QuizRecords WHERE type='table'", null);
//
//        if (c.moveToFirst()) {
//            while ( !c.isAfterLast() ) {
//                if (c.getPosition()==0) {
//                    assertEquals("Name of first table in database is quizEntry", "quizEntry", c.getString(0));
//                } else if (c.getPosition() == 1){
//                    assertEquals("Name of first table in database is categoryEntry", "categoryEntry", c.getString(0));
//                } else {
//                    Log.d(LOG_TAG, "too many tables created by QuizDBHelper at testTableCreation");
//                }
//                c.moveToNext();
//            }
//        }
//        c.close();
//        db.close();
//    }

//    public void testQuizDataEntry(){
//        SQLiteDatabase sqLiteDatabase = mQuizDBHelper.getWritableDatabase();
//        ContentValues quizValues = new ContentValues();
//        long returnValue = 0;
//        quizValues.put(QuizDBContract.QuizEntry.COLUMN_NAME_QUIZ_SIZE,10);
//        quizValues.put(QuizDBContract.QuizEntry.COLUMN_NAME_SCORE, 7);
//        quizValues.put(QuizDBContract.QuizEntry.COLUMN_NAME_AVERAGE_TIME_OVERALL, 7.0);
//        quizValues.put(QuizDBContract.QuizEntry.COLUMN_NAME_AVERAGE_TIME_CORRECT, 7.0);
//        quizValues.put(QuizDBContract.QuizEntry.COLUMN_NAME_AVERAGE_TIME_WRONG, 7.0);
//        try {
//            returnValue = sqLiteDatabase.insertOrThrow(QuizDBContract.QuizEntry.TABLE_NAME, null, quizValues);
//        } catch (SQLException e){
//            Log.d(LOG_TAG, "error while inserting into quizEntry table at testQuizDataEntry(): " + e.getMessage());
//        }
//        Cursor c = sqLiteDatabase.query(QuizDBContract.QuizEntry.TABLE_NAME, new String[]{QuizDBContract.QuizEntry.COLUMN_NAME_QUIZ_SIZE, QuizDBContract.QuizEntry.COLUMN_NAME_AVERAGE_TIME_CORRECT}, null,null,null,null,null);
//        if (c.moveToFirst()) {
//            while ( !c.isAfterLast() ) {
//                if (c.getPosition()==returnValue) {
//                    assertEquals("Size of returnValue-th quiz is 10", 10, c.getString(0));
//                    assertEquals("Average time on correct answers of returnValue-th quiz is 7.0", 7.0, c.getDouble(1));
//                } else {
//                    Log.d(LOG_TAG, "too many records created at testQuizDataEntry()");
//                }
//                c.moveToNext();
//            }
//        }
//        c.close();
//        sqLiteDatabase.close();
//    }
//
//    public void testCategoryDataEntry(){
//        SQLiteDatabase sqLiteDatabase = mQuizDBHelper.getWritableDatabase();
//        ContentValues categoryValues = new ContentValues();
//        long returnValue = 0;
//        categoryValues.put(QuizDBContract.CategoryEntry.COLUMN_NAME_NAME,"general");
//        categoryValues.put(QuizDBContract.CategoryEntry.COLUMN_NAME_TOTAL_QUESTIONS_ANSWERED, 7);
//        categoryValues.put(QuizDBContract.CategoryEntry.COLUMN_NAME_CORRECTLY_ANSWERED, 5);
//        categoryValues.put(QuizDBContract.CategoryEntry.COLUMN_NAME_TOTAL_TIME_OVERALL, 7.0);
//        categoryValues.put(QuizDBContract.CategoryEntry.COLUMN_NAME_TOTAL_TIME_CORRECT, 7.0);
//        categoryValues.put(QuizDBContract.CategoryEntry.COLUMN_NAME_TOTAL_TIME_WRONG, 7.0);
//        try {
//            returnValue = sqLiteDatabase.insertOrThrow(QuizDBContract.CategoryEntry.TABLE_NAME, null, categoryValues);
//        } catch (SQLException e){
//            Log.d(LOG_TAG, "error while inserting into categoryEntry table at testCategoryDataEntry(): " + e.getMessage());
//        }
//        Cursor c = sqLiteDatabase.query(QuizDBContract.CategoryEntry.TABLE_NAME, new String[]{QuizDBContract.CategoryEntry.COLUMN_NAME_NAME, QuizDBContract.CategoryEntry.COLUMN_NAME_TOTAL_QUESTIONS_ANSWERED, QuizDBContract.CategoryEntry.COLUMN_NAME_TOTAL_TIME_OVERALL}, null,null,null,null,null);
//        if (c.moveToFirst()) {
//            while ( !c.isAfterLast() ) {
//                if (c.getPosition()==returnValue) {
//                    assertEquals("Name of returnvalue-th category is 'general'","general",c.getString(0));
//                    assertEquals("Total answered questions of returnValue-th category is 7", 7, c.getInt(1));
//                    assertEquals("Average time on all answers of returnValue-th quiz is 7.0", 7.0, c.getDouble(2));
//                } else {
//                    Log.d(LOG_TAG, "too many records created at testCategoryDataEntry()");
//                }
//                c.moveToNext();
//            }
//        }
//        c.close();
//        sqLiteDatabase.close();
//    }
}
