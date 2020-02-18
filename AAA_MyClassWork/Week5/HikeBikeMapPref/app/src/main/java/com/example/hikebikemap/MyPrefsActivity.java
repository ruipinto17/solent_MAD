package com.example.hikebikemap;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class MyPrefsActivity extends PreferenceActivity
{
    public void onCreate (Bundle savedInstaceState)
    {
        super.onCreate(savedInstaceState);
        addPreferencesFromResource(R.xml.preferences);
    }

}

