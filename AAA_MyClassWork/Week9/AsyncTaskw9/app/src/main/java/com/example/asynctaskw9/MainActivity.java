package com.example.asynctaskw9;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements OnClickListener
{
    private static final String BASE_URL = "http://www.free-map.org.uk/course/ws/hits.php";
    private static final String ARTIST_QUERY = "artist=";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button fetchSongs = findViewById(R.id.btnGetSongs);
        fetchSongs.setOnClickListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.async_menu, menu);
        return true;
    }

    /*public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == R.id.addArtist)
        {
            Intent intent = new Intent(this, PostArtistActivity.class);
            start
        }
    }*/

    public void onClick(View view)
    {
        EditText artist = findViewById(R.id.artist);
        MainActivity.GetSongsTask t = new MainActivity.GetSongsTask();
        t.execute(artist.getText().toString());
    }

    class GetSongsTask extends AsyncTask<String, Void, String>
    {
        @Override
        public String doInBackground(String... input)
        {
            String artist = input[0];
            String queryUrl = BASE_URL + "?" + ARTIST_QUERY + artist;
            HttpURLConnection conn = null;
            try
            {
                //url object to represent a URL
                URL url = new URL(queryUrl);
                //open a connection to the URL using our HttpURLConnection object
                conn = (HttpURLConnection) url.openConnection();
                //input stream to read data in from the URL
                InputStream in = conn.getInputStream();

                //we test that the HTTP code was returned. If a code other than 200 OK was returned
                // it means there's been a server side error so we don't want to process the results
                if (conn.getResponseCode() == 200)
                {
                    //if the code was 200 we create a BufferedReader from the InputStream, remember that Reader allow us to read the text as Unicode.
                    //First of all we convert the InputStream into a Reader using InputStreamReader and then into a BufferedReader,
                    // to allow data to be buffered in memory as it is read in, for efficiency. We then use the technique first seen in the File I/O topic
                    // to read in each line, and (to keep things simple) add each line to the String result.
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    StringBuffer result = new StringBuffer();
                    String line;
                    while ((line = br.readLine()) != null)
                    {
                        result.append(line).append("\n");
                    }
                    //we then return the result from the doInBackground() method, and the return value of doInBackground() is automatically
                    // passed to onPostExecute() as a parameter.
                    return result.toString();
                } else
                {
                    return "HTTP ERROR: " + conn.getResponseCode();
                }
            }
            catch (IOException e)
            {
                return e.toString();
            }
            finally
            {
                if (conn != null)
                {
                    conn.disconnect();
                }
            }
        }

        public void onPostExecute(String result)
        {
            TextView songList = findViewById(R.id.songList);
            songList.setText(result);
        }
    }
}
