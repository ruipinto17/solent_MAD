package com.example.hikebikemap;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public class MainActivity extends AppCompatActivity {

    private Double latitude = 51.05;
    private Double longitude = -0.72;
    private Integer zoom = 16;
    private String mapCode = null;

    MapView mv;

    //called when the activity is first created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //load last used mapcode if available
        if(savedInstanceState != null)
        {
            mapCode = savedInstanceState.getString("com.example.mapcode");
        }

        //load map code if no last used mapcode
        if(mapCode == null)
        {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            mapCode = prefs.getString("mapPref", "normal");
        }

        //this line sets the user agent, a requirement to download OSM maps
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_main);

        centerMap();
    }

    public void onStart()
    {
        super.onStart();
        centerMap();
    }

    public void onDestroy()
    {
        super.onDestroy();
        //save the chosen map
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("com.example.mapcode", mapCode);
        editor.commit();
    }

    public void onResume()
    {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        try
        {
            double lat = Double.parseDouble(prefs.getString("lat", "42.2"));
            double lon = Double.parseDouble(prefs.getString("lon", "-42.2"));
        }
        catch(Exception ex)
        {
            Toast.makeText(getApplicationContext(), "An error has occurred: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        boolean autodownload = prefs.getBoolean("autodownload", true);
        String mapCode = prefs.getString("map", "normal");
    }

    public void onSaveInstanceState (Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("com.example.mapcode", mapCode);
    }

    private void centerMap()
    {
        mv = findViewById(R.id.map1);
        mv.setMultiTouchControls(true);
        mv.getController().setZoom(16.0);
        mv.getController().setCenter(new GeoPoint(latitude, longitude));
    }

    //this method makes the menu appear
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //reaction of the user choosing the map
        if (item.getItemId() == R.id.choosemap)
        {
            //creating a second intent
            Intent intent = new Intent(this, MapChooseActivity.class);
            /*this line launches the second activity and expects a result to be sent back,
            the 0 is an ID that we use to determine which child activity produced the results,
            a parent activity could launch several child activities, so when we get a result,
            we need to identify which child activity produced the result).*/
            startActivityForResult(intent, 0);
            return true;
        }
        else if(item.getItemId() == R.id.setlocation)
        {
            Intent requestIntent = new Intent(this, SetLocation.class);
            Bundle bundle = new Bundle();
            bundle.putDouble("com.example.latitude", latitude);
            bundle.putDouble("com.example.longitude", longitude);
            bundle.putInt("com.example.zoom", zoom);
            requestIntent.putExtras(bundle);

            startActivityForResult(requestIntent, 1);
            return true;
        }
        else if(item.getItemId() == R.id.preferences)
        {
            //start set defaults activity
            Intent requestIntent = new Intent(this, MyPrefsActivity.class);
            startActivityForResult(requestIntent, 2);
            return true;
        }
        return false;
    }

    /*take 3 parameters, including the requestCode (the ID of 0 that we used to identify our activity launch),
    the resultCode (the code that the second Activity send back - RESULT_OK in this case) and
    the Intent used to send the result back to the first activity.
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //checks if the results was sent back from a request with an ID of 0
        if (requestCode == 0 && resultCode == RESULT_OK)
        {
            //we retrieve the button ID sent back from the second activity within the Bundle
            Bundle extras = intent.getExtras();
            //depending on what was sent back from the second activity
            boolean hikebikemap = extras.getBoolean("com.example.hikebikemap");
            //we turn the hikebikemap on
            if (hikebikemap == true)
            {
                mv.setTileSource(TileSourceFactory.HIKEBIKEMAP);
            }
            //or off
            else
            {
                mv.setTileSource(TileSourceFactory.MAPNIK);
            }
        }
        else if(requestCode == 1 && resultCode == RESULT_OK)
        {
            Bundle extras = intent.getExtras();
            latitude = extras.getDouble("com.example.latitude");
            longitude = extras.getDouble("com.example.longitude");
            zoom = extras.getInt("com.example.zoom");

            centerMap();
        }
        else if (requestCode == 2)
        {
            //result from set preferences activity
            //test results and relaunch if incorrect
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            Double lat;
            Double lon;
            Integer zom;

            try
            {
                lat = Double.parseDouble(prefs.getString("lat", "51.05"));
                lon = Double.parseDouble(prefs.getString("lon", "-0.72"));
                zom = Integer.parseInt(prefs.getString("zoom", "16"));

                latitude = lat;
                longitude = lon;
                zoom = zom;
            }
            catch (Exception ex)
            {
                Intent requestIntent = new Intent(this, MyPrefsActivity.class);
                startActivityForResult(requestIntent, 2);
            }

        }
    }
}

