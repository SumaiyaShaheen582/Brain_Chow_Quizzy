package com.avinashdavid.trivialtrivia.acknowledgments;

import android.net.Uri;

/**
 * Created by avinashdavid on 12/5/16.
 */

/**
 * This is a very simple class that defines the model for content and image acknowledgments.
 * The class creates and returns an object that will be used to display content and image acknowledgments in the app.
 * Each acknowledgment has a name (that is displayed) and a link (that is launched on click)
 */
public class AcknowledgmentObject {
    private String name;
    private Uri link;

    //constructor with string and uri parameters for name and link
    public AcknowledgmentObject(String name, Uri link) {
        this.name = name;
        this.link = link;
    }

    //constructor with string parameters for name and link, with link uri being parsed from the second string parameter
    public AcknowledgmentObject(String name, String link){
        this.name = name;
        this.link = Uri.parse(link);
    }

    public String getName() {
        return name;
    }

    public Uri getLink() {
        return link;
    }
}
