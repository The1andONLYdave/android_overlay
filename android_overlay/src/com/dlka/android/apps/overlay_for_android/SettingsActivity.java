package com.dlka.android.apps.overlay_for_android;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.widget.Toast;

import com.dlka.android.apps.overlay_for_android.R;

public class SettingsActivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        SharedPreferences prefs = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = prefs.edit();
       // ed.putBoolean("HaveShownPrefs", true);
        ed.putBoolean("HaveShownPrefs", false);
        ed.commit();
        Toast.makeText(this, "Bitte Timer einstellen", Toast.LENGTH_LONG).show();
        
    }
}