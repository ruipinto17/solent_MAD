package com.example.hikebikemap;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;

public class MyPrefsActivity extends PreferenceActivity //implements Preference.OnPreferenceChangeListener
{
    public void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        addPreferencesFromResource(R.xml.preferences);
    }

}


