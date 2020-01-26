package com.avinashdavid.trivialtrivia.DataTesting;

import android.test.AndroidTestCase;

import com.avinashdavid.trivialtrivia.data.QuizDBContract;

/**
 * Created by avinashdavid on 11/7/16.
 */

public class QuizDBContractTesting extends AndroidTestCase {
    public void testUriGenerationMethods(){
        assertEquals("buildUriQuizId(3) returns the expected Uri","content://com.avinashdavid.trivialtrivia.data/quiz/3", QuizDBContract.QuizEntry.buildUriQuizId(3).toString());
        assertEquals("buildUriCategoryId(4) returns the expected Uri","content://com.avinashdavid.trivialtrivia.data/category/4", QuizDBContract.CategoryEntry.buildUriCategoryId(4).toString());
        assertEquals("buildUriCategoryName('general') returns the expected Uri","content://com.avinashdavid.trivialtrivia.data/category/1", QuizDBContract.CategoryEntry.buildUriCategoryName("general").toString());
    }
}
