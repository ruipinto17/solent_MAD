package com.example.hikebikemap;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class SetLocation extends AppCompatActivity implements OnClickListener
{
    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_location);

        Button b = findViewById(R.id.btnGo);
        b.setOnClickListener(this);

    }


    public void onClick (View view)
    {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();

        Double latitude;
        Double longitude;
        Integer zoom = 16;

        EditText etLat = findViewById(R.id.txtLat);
        EditText etLon = findViewById(R.id.txtLon);

        try
        {
            Double lat = Double.parseDouble(etLat.getText().toString());
            Double lon = Double.parseDouble(etLon.getText().toString());

            latitude = lat;
            longitude = lon;

            bundle.putDouble("com.example.latitude", latitude);
            bundle.putDouble("com.example.longitude", longitude);
            bundle.putInt("com.example.zoom", zoom);

            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            finish();
        }
        catch(Exception ex)
        {
            new AlertDialog.Builder(this).setPositiveButton("OK", null).setMessage("Invalid input").show();
            etLat.getText().clear();
            etLon.getText().clear();
            etLat.requestFocus();
        }

    }
}
