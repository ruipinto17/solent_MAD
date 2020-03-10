package com.example.addressbook;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {

    EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = findViewById(R.id.edit_text);
    }

    public void save(View v)
    {
        String text = mEditText.getText().toString();
        FileOutputStream fos = null;

        try
        {
            fos = openFileOutput("example.txt", MODE_PRIVATE);
            fos.write(text.getBytes());

            mEditText.getText().clear();
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + "example.txt", Toast.LENGTH_LONG).show();
        }
        catch (IOException e)
        {
            new AlertDialog.Builder(this).setPositiveButton("OK", null).setMessage("ERROR: " + e).show();
        }
        finally
        {
            if (fos != null)
            {
                try
                {
                    fos.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public void load(View v) {

        FileInputStream fis = null;

        try
        {
            fis = openFileInput("example.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null)
            {
                sb.append(text).append("\n");
            }
            mEditText.setText(sb.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (fis != null)
            {
                try
                {
                    fis.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

    }


    //region OptionsItemSelected region
    /*

     public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        etText = findViewById(R.id.etText);

        if (item.getItemId() == R.id.save)
        {
            public void save(View v)
            {
                try
                {

                    String text = etText.getText().toString();
                    FileOutputStream fileOutputStream = null;

                    fileOutputStream = openFileOutput(FILE_NAME, MODE_PRIVATE);

                }
                catch(IOException e)
                {
                    new AlertDialog.Builder(this).setPositiveButton("OK", null).setMessage("ERROR: " + e).show();
                }
            }
            return true;
        }
        if (item.getItemId() == R.id.load) {
            return true;
        }
        return false;
    }*/
    //endregion


}
