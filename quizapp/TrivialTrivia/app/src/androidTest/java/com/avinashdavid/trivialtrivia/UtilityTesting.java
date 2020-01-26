package com.avinashdavid.trivialtrivia;

import android.test.AndroidTestCase;
import android.util.Log;

/**
 * Created by avinashdavid on 11/4/16.
 */

public class UtilityTesting extends AndroidTestCase {
    private static final String LOG_TAG = "UtilityTesting";

    public void testArrayAveraging(){
        assertEquals("Calculate 40% when given a total of 5 and an actual of 2",40.0,Utility.calculatePercentage(5,2));
        int[] testCase = new int[5];
        testCase[0] = 3;
        testCase[1] = 2;
        testCase[2] = 4;
        testCase[3] = -1;
        testCase[4] = 3;

        assertEquals("Average of testCase list is 3",3.00,Utility.getAverageFromIntListWITHNegativeValueElimination(testCase));

        int[] testActual = new int[]{7,5,8,4,6,6};
        int[] testTotal = new int[]{10,10,10,10,10};
        try {
            assertEquals("Average percentage of testActual in testTotal is 60.0%", 60.0, Utility.getAveragePercentageFromListsOfActualAndTotal(testActual, testTotal));
        } catch (Exception e){
            Log.d(LOG_TAG, "error at Utility.getAveragePercentageFromListsOfActualAndTotal: " + e.getMessage());
        }
    }
}
