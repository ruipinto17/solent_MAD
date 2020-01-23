package com.example.myapplication;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = findViewById(R.id.btnOK);
        Button c = findViewById(R.id.btnClear);
        b.setOnClickListener(this);
        c.setOnClickListener(this);
    }

    public void onClick (View view)
    {
        EditText txtPanel = findViewById(R.id.txtProblem);

        switch (view.getId())
        {
            case R.id.btnOK: //ok button


                String msg = txtPanel.getText().toString();

                //tell the user that he didn't enter any text on the box
                if(msg.isEmpty())
                {
                    new AlertDialog.Builder(this).setNegativeButton("OK",null).setMessage("Empty text").show();
                }
                else
                {
                    //int i = numberClicks.addAndGet(1);
                    new AlertDialog.Builder(this).setPositiveButton("OK",null).setMessage("Thank you for reporting the problem").show();
                    txtPanel.setText("");

                }
                break;

            case R.id.btnClear:
                txtPanel.setText("");
                txtPanel.setHint(R.string.initialMessage);
                break;

            default:
                break;
        }
    }
}
