package com.example.mapping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;


public class MapChooseActivity extends AppCompatActivity implements OnClickListener {
    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_choose);

        //when user presses one of the buttons in the second activity
        Button regular = findViewById(R.id.btnRegular);
        regular.setOnClickListener(this);
        Button hikebikemap = findViewById(R.id.btnHikeBikeMap);
        hikebikemap.setOnClickListener(this);
    }

    public void onClick (View v)
    {
        Intent intent = new Intent();
        //collection of data which can be passed around between Activities (collection of key value pairs)
        Bundle bundle = new Bundle();
        //this bundle contains a boolean storing whether the hikebikemap is being used or not
        boolean hikebikemap = false;

        /*we find out by knowing which button was pressed (using the getId() method of the passed into onClick)
        and if the hikebikemap was pressed we set it to true*/
        if(v.getId()==R.id.btnHikeBikeMap)
        {
            hikebikemap = true;
        }

        /*proper label of the entry in the bundle uses the domain name as the identifier
        ("com.example.hikebikemap") so it can be identified uniquely*/
        bundle.putBoolean("com.example.hikebikemap", hikebikemap);
        //adding it to the intent
        intent.putExtras(bundle);
        /*sending a result back to the parent activity (a result works as a little like a return code for a function),
        we send back the RESULT_OK to indicate the calling activity (in this case, the main activity) that the secondary
        activity was completed successfully (RESULT_CANCELED, indicates that the user cancelled the action in the second activity)*/
        setResult(RESULT_OK, intent);
        //finally we force the activity to finish
        finish();

    }
}
