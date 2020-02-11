package com.example.mapping;

import android.service.quicksettings.TileService;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;


public class MainActivity extends AppCompatActivity {

    private Double latitude = 51.05;
    private Double longitude = -0.72;
    private Integer zoom = 16;

    MapView mv;

    //called when the activity is first created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //this line sets the user agent, a requirement to download OSM maps
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_main);

        centerMap();


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
    }
}

