package com.example.hikebikemap2;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    boolean isRecording = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null)
        {
            isRecording = savedInstanceState.getBoolean("isRecording");
        }
    }

    //region why do we place the code in the onResume()
    /*
    * Why is the code on the onResume() method ? The onResume() method is called when the activity re-appears, having been hidden.
    * When preferences are changed via a PreferenceActivity, the user will most likely press the Back button to dismiss the
    * PreferenceActivity, resulting the main activity appearing again and therefore its onResume() method being called, that's
    * why this is a good place to put code that reads preferences
    * */
    //endregion

    //region SharedPreferences object
    /*
    * We get it from PreferenceManager.getDefaultSharedPreferences and it takes a parameter (context object)
    * this object represents the context of the preferences, we can have preferences which can relate to the
    * application as a whole or to an individual activity, here we use getApplicationContext() to indicate that
    * we want the preferences relating the whole application (preferences that can be accessed in all activities).
    * The activity class is a subclass of Context, as is Application, the class representing the application as a whole.
    * This means that the return value of getApplicationContext() could be either an Activity or an Application, in this
    * case its the application. Many UI elements, such as dialog boxes, require a Context of some kind
    * (usually, but not always, an Activity) as a parameter in the constructor, which represents the "parent" element of that UI element.
    * */
    //endregion
    public void onResume()
    {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        double lat = Double.parseDouble ( prefs.getString("lat", "50.9") );
        double lon = Double.parseDouble ( prefs.getString("lon", "-1.4") );
        boolean autodownload = prefs.getBoolean("autodownload", true);
        String pizzaCode = prefs.getString("pizza", "NONE");

        // do something with the preference data...
    }

    public void onDestroy()
    {
        super.onDestroy();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean ("isRecording", isRecording);
        editor.commit();
    }

    public void onSaveInstanceState (Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean("isRecording", isRecording);
    }

}
