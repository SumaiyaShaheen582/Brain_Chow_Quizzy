package com.avinashdavid.trivialtrivia.acknowledgments;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by avinashdavid on 12/5/16.
 */

/**
 * This class provides the model for acknowledgments.
 * Each acknowledgment has been explicitly created. No constructor has been provided, choosing instead to create the acknowledgments on demand via a method call
 */
public class AcknowledgmentStorage {
    private static ArrayList<AcknowledgmentObject> sQuestionsAcknowledgmentObjects;
    private static ArrayList<AcknowledgmentObject> sImagesAcknowledgementObjects;

//    private AcknowledgmentStorage() {
//        if (sQuestionsAcknowledgmentObjects == null){
//            sQuestionsAcknowledgmentObjects = createQuestionAcknowledgmentArrayList();
//        }
//        if (sImagesAcknowledgementObjects == null){
//            sImagesAcknowledgementObjects = createImageAcknowledgementArrayList();
//        }
//    }

    //static factory method for content acknowledgments
    public static ArrayList<AcknowledgmentObject> getQuestionsAcknowledgmentObjects() {
        if (sQuestionsAcknowledgmentObjects == null){
            sQuestionsAcknowledgmentObjects = createQuestionAcknowledgmentArrayList();
        }
        return sQuestionsAcknowledgmentObjects;
    }

    //static factory method for image acknowledgments
    public static ArrayList<AcknowledgmentObject> getImagesAcknowledgementObjects() {
        if (sImagesAcknowledgementObjects == null){
            sImagesAcknowledgementObjects = createImageAcknowledgementArrayList();
        }
        return sImagesAcknowledgementObjects;
    }

    //method to explicitly add all content acknowledgments
    private static ArrayList<AcknowledgmentObject> createQuestionAcknowledgmentArrayList(){
        ArrayList<AcknowledgmentObject> returnList = new ArrayList<>();
        returnList.add(new AcknowledgmentObject("IndiaBIX", "http://www.indiabix.com/"));
        returnList.add(new AcknowledgmentObject("Mental Floss", "http://mentalfloss.com/"));
        returnList.add(new AcknowledgmentObject("ActionQuiz", "http://www.actionquiz.com/"));
        returnList.add(new AcknowledgmentObject("Businessballs", "http://www.businessballs.com/"));
        returnList.add(new AcknowledgmentObject("The People History", "http://www.thepeoplehistory.com/"));
        returnList.add(new AcknowledgmentObject("Table Quiz Central", "http://www.tablequizcentral.com/"));
        returnList.add(new AcknowledgmentObject("Maps of World", "http://www.mapsofworld.com/"));
        returnList.add(new AcknowledgmentObject("The Science Spot", "http://sciencespot.net/"));
        returnList.add(new AcknowledgmentObject("Wikipedia", "https://www.wikipedia.org/"));
        returnList.add(new AcknowledgmentObject("Think Fact Youtube Video", "https://youtu.be/n5-vhdkggws"));
        return returnList;
    }

    //method to explicitly add all image acknowledgments
    private static ArrayList<AcknowledgmentObject> createImageAcknowledgementArrayList(){
        ArrayList<AcknowledgmentObject> returnList = new ArrayList<>();
        returnList.add(new AcknowledgmentObject("Vlad Marin on Iconfinder", "https://www.iconfinder.com/quizanswers"));
        return returnList;
    }
}
