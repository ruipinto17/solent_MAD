package com.example.mappingui;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity implements OnClickListener
{
    MapView mv;

    //called when the activity is first created
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //this line sets the user agent, a requirement to download OSM maps
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_main);

        mv = findViewById(R.id.map1);

        mv.setMultiTouchControls(true);
        mv.getController().setZoom(16.0);
        mv.getController().setCenter(new GeoPoint(50.9000852, -1.4042018,17));

        Button b = findViewById(R.id.btnGo);
        b.setOnClickListener(this);
    }

    public void onClick(View view)
    {
        TextView tvLat = findViewById(R.id.lblLat);
        TextView tvLon = findViewById(R.id.lblLon);

        EditText etLat = findViewById(R.id.txtLat);
        EditText etLon = findViewById(R.id.txtLon);

        try
        {
            Double lat = Double.parseDouble(etLat.getText().toString());
            Double lon = Double.parseDouble(etLon.getText().toString());

            mv.getController().setCenter(new GeoPoint(lat,lon));
        }
        catch (NumberFormatException ex)
        {
            new AlertDialog.Builder(this).setPositiveButton("OK", null).setMessage("Invalid input").show();
            etLat.getText().clear();
            etLon.getText().clear();
            etLat.requestFocus();
        }
    }
}
