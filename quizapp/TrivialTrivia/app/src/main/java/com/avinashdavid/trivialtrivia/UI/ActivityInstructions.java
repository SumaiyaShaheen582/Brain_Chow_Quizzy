package com.avinashdavid.trivialtrivia.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;

import com.avinashdavid.trivialtrivia.R;

public class ActivityInstructions extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FragmentGameplayInstructions fragmentGameplayInstructions = new FragmentGameplayInstructions();
        getSupportFragmentManager().beginTransaction().replace(R.id.container_instructions, fragmentGameplayInstructions).commit();
    }

    @Override
    protected void onPause() {
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        super.onPause();
    }

}
