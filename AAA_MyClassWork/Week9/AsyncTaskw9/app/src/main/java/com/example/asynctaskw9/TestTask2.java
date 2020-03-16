package com.example.asynctaskw9;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;

public class TestTask2 extends AsyncTask<String, Void, String> {
    Context parent;

    public TestTask2(Context p) {
        //doInBackground("http...");
        parent = p;
    }

    protected String doInBackground(String... urls) {
        String message = "Successfully downloaded!";

        try {
            String urlToDownloadFrom = urls[0];
            //download from that URL...

            throw new IOException("test");
        } catch (IOException e) {
            message = e.toString();
        }
        return null;
    }

    public void onPostExecute(String message) {
        new AlertDialog.Builder(parent).setMessage(message).setPositiveButton("OK", null).show();
    }
}
