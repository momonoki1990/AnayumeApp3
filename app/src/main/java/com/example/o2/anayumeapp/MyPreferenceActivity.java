package com.example.o2.anayumeapp;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class MyPreferenceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new MyPreferenceFragment()).commit();
    }
}
