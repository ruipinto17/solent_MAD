package com.example.hikebikemap;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;

public class MyPrefsActivity extends PreferenceActivity //implements Preference.OnPreferenceChangeListener
{
    public void onCreate (Bundle savedInstaceState)
    {
        super.onCreate(savedInstaceState);
        addPreferencesFromResource(R.xml.preferences);

        /*EditTextPreference  pref1 = (EditTextPreference) findPreference(R.xml.lat);
        pref1.setOnPreferenceChangeListener(this);*/

    }

     //PreferenceActivity p;

/*
    @Override
    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (!(preference instanceof EditTextPreference && obj instanceof String)) {
            return false;
        }

        EditTextPreference editTextPreference = (EditTextPreference) preference;
        String key = editTextPreference.getKey();
        String oldValue = editTextPreference.getText();
        String newValue = String.valueOf(obj);

        if (newValue.isEmpty()) return false;
        return true;
    }*/

}

