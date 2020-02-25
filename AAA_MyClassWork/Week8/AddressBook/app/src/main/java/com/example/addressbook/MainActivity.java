package com.example.addressbook;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save)
        {

            EditText et = (EditText) findViewById(R.id.etText);
            try
            {
                String text = et.getText().toString();

                PrintWriter pw = new PrintWriter(new FileWriter("data.txt"));

                pw.println("Hello");
                pw.println("It's a nice day!");
                pw.close(); // close the file to ensure data is flushed to file
            }
            catch (IOException e)
            {
                new AlertDialog.Builder(this).setPositiveButton("OK", null).setMessage("ERROR: " + e).show();
            }

            return true;
        }
        if(item.getItemId() == R.id.load)
        {
            return true;
        }
        return false;
    }


}
