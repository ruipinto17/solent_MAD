package com.example.mapping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;


public class SetLocation extends AppCompatActivity implements OnClickListener
{

    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_location);

        //when user presses one of the buttons in the second activity
        Button regular = findViewById(R.id.btnRegular);
        regular.setOnClickListener(this);
        Button hikebikemap = findViewById(R.id.btnHikeBikeMap);
        hikebikemap.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        TextView tvLat = findViewById(R.id.lblLat);
        TextView tvLon = findViewById(R.id.lblLon);

        EditText etLat = findViewById(R.id.txtLat);
        EditText etLon = findViewById(R.id.txtLon);

        Button btn = findViewById(R.id.btnGo);


        Intent intent = new Intent();

        Bundle bundle = new Bundle();

        boolean setlocation = false;

        if(v.getId() == R.id.btnGo);
        {
            setlocation = true;
        }

        bundle.putBoolean("com.example.setlocation", setlocation);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }

}
