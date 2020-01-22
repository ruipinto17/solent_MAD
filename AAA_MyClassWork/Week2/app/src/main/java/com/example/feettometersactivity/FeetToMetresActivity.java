package com.example.feettometersactivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;

public class FeetToMetresActivity extends AppCompatActivity implements OnClickListener {
    //called when the activity is first created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = findViewById(R.id.btnConvert);
        b.setOnClickListener(this);
    }

    public void onClick(View view)
    {
        TextView tv = findViewById(R.id.lblResult);
        EditText et = findViewById(R.id.txtInput);
        //Double feet = 0.0;
        try
        {
           double feet = Double.parseDouble(et.getText().toString());
           double metres = feet * 0.305;
           tv.setText(feet +" feet = " + metres + " metres" );
        }
        catch (NumberFormatException ex)
        {
            new AlertDialog.Builder(this).setPositiveButton("OK", null).setMessage("The input entered is invalid").show();
            tv.setText("");
        }
        //Double metres = feet * 0.305;
        //tv.setText("In meters that is: " + metres);
    }
}
