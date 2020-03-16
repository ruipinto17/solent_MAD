package com.example.asynctaskw9;

import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;

public class MyActivity extends AppCompatActivity {
    class InnerTestTask extends AsyncTask<String, Void, String> {
        public String doInBackground(String... urls) {
            String message = "Successfully downloaded!";
            try {
                String urlToDownloadFrom = urls[0];
                //download from that URL
                throw new IOException("test");
            } catch (IOException e) {
                message = e.toString();
            }
            return message;
        }

        public void onPostExecute(String message) {
            new AlertDialog.Builder(MyActivity.this).setMessage(message).setPositiveButton("OK", null).show();
        }
    }
    //other activity code omitted

    //Example onClick() which would be linked to a button press
    public void onClick() {
        //we create the asynctask passing the current activity to it, this is passed in as a parameter of data type Context
        //TestTask task = new TestTask(this);
        //we then call execute to start the AsyncTask running, this will automatically create a separate thread to run the AsyncTask in
        //task.execute();
        //TestTask2 task = new TestTask2(this);
        //task.execute("https://hikar.org/index.php");

        InnerTestTask task = new InnerTestTask();

        //this could also be read in from the UI
        String filename = Environment.getExternalStorageDirectory().getAbsolutePath() + "/async2.txt";
        task.execute(filename);
    }
}
