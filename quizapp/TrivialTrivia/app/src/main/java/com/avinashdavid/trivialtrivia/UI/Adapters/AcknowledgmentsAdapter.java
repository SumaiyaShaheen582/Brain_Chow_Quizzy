package com.avinashdavid.trivialtrivia.UI.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.avinashdavid.trivialtrivia.R;
import com.avinashdavid.trivialtrivia.acknowledgments.AcknowledgmentObject;

import java.util.ArrayList;

import static android.R.attr.resource;

/**
 * Created by avinashdavid on 12/5/16.
 */

public class AcknowledgmentsAdapter extends ArrayAdapter<AcknowledgmentObject> {
    Context mContext;
    ArrayList<AcknowledgmentObject> mData;
    public AcknowledgmentsAdapter(Context context, ArrayList<AcknowledgmentObject> objects) {
        super(context, 0, objects);
        mContext = context;
        mData = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final AcknowledgmentObject acknowledgmentObject = getItem(position);
        if (convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_acknowledgments, parent, false);
        }

        TextView name = (TextView)convertView.findViewById(R.id.textview_name);
        TextView link = (TextView)convertView.findViewById(R.id.textview_link);

        name.setText(acknowledgmentObject.getName());
        link.setText(acknowledgmentObject.getLink().toString());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, acknowledgmentObject.getLink());
                if (intent.resolveActivity(mContext.getPackageManager())!=null){
                    mContext.startActivity(intent);
                }
            }
        });

        return convertView;
    }
}
