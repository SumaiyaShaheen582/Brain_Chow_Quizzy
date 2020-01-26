package com.avinashdavid.trivialtrivia.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.avinashdavid.trivialtrivia.R;
import com.avinashdavid.trivialtrivia.UI.Adapters.AcknowledgmentsAdapter;
import com.avinashdavid.trivialtrivia.acknowledgments.AcknowledgmentObject;
import com.avinashdavid.trivialtrivia.acknowledgments.AcknowledgmentStorage;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class ActivityAcknowledgments extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acknowledgments);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ListView contentAcknowledgmentsListview = (ListView)findViewById(R.id.listview_content_acknowledgments);
        ListView imageAcknowledgmentsListView = (ListView)findViewById(R.id.listview_image_acknowledgments);

        ArrayList<AcknowledgmentObject> contentAcknowledgments = AcknowledgmentStorage.getQuestionsAcknowledgmentObjects();
        ArrayList<AcknowledgmentObject> imageAcknowledgments = AcknowledgmentStorage.getImagesAcknowledgementObjects();

        contentAcknowledgmentsListview.setAdapter(new AcknowledgmentsAdapter(this, contentAcknowledgments));
        imageAcknowledgmentsListView.setAdapter(new AcknowledgmentsAdapter(this, imageAcknowledgments));

        ListUtils.setDynamicHeight(contentAcknowledgmentsListview);
        ListUtils.setDynamicHeight(imageAcknowledgmentsListView);
    }

    public static class ListUtils {
        public static void setDynamicHeight(ListView mListView) {
            ListAdapter mListAdapter = mListView.getAdapter();
            if (mListAdapter == null) {
                // when adapter is null
                return;
            }
            int height = 0;
            int desiredWidth = View.MeasureSpec.makeMeasureSpec(mListView.getWidth(), View.MeasureSpec.UNSPECIFIED);
            for (int i = 0; i < mListAdapter.getCount(); i++) {
                View listItem = mListAdapter.getView(i, null, mListView);
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                height += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = mListView.getLayoutParams();
            params.height = height + (mListView.getDividerHeight() * (mListAdapter.getCount() - 1));
            mListView.setLayoutParams(params);
            mListView.requestLayout();
        }
    }

    @Override
    protected void onPause() {
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        super.onPause();
    }

}
