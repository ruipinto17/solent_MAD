package com.example.asynctaskw9;

import android.support.v7.app.AppCompatActivity;

public class MyActivity extends AppCompatActivity {
    //other activity code omitted

    //Example onClick() which would be linked to a button press
    public void onClick() {
        //we create the asynctask passing the current activity to it, this is passed in as a parameter of data type Context
        //TestTask task = new TestTask(this);
        //we then call execute to start the AsyncTask running, this will automatically create a separate thread to run the AsyncTask in
        //task.execute();

        TestTask2 task = new TestTask2(this);
        task.execute("https://hikar.org/index.php");
    }
}
