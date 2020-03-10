package com.example.asynctaskw9;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;

/*Void,Void,String represents the data types of the inputs to the AsyncTask

* First parameter doInBackground() - There won't be any input to the AsyncTask-the filename is alawys "asynctext.txt"
*
* The second void represents the data type of a variable that can be used to indicate the progress of the AsyncTask, which could be used to display a progress bar
* for example. Here we are not showing progress therefore this is a Void again
*
* the String represents the data type of the return value of the doInBackground() method
* */

public class TestTask extends AsyncTask<Void,Void,String>
{
    Context parent;

    public TestTask(Context p)
    {
        parent = p;
    }

    @Override
    protected String doInBackground(Void... unused)
    {
        String message = "Successfully downloaded!";
        try
        {
            //network communication would go here;
        }
        catch(IOException e)
        {
            message = e.toString();
        }
        return message;
    }

    /* onPostExecute method can include code to display GUI Elements, as this runs in the main UI thread*/
    public void onPostExecute (String message)
    {
        new AlertDialog.Builder(parent).setMessage(message).setPositiveButton("OK", null).show();
    }
}
