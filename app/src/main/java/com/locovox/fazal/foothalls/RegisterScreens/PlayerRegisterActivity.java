package com.locovox.fazal.foothalls.RegisterScreens;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.locovox.fazal.foothalls.R;
import com.locovox.fazal.foothalls.SQLite.DatabaseHelper;

public class PlayerRegisterActivity extends AppCompatActivity {

    MaterialSpinner positionSpinner;
    EditText playername,playeremail,playerage,password,confirmpass;
    Button register;
    String playerNameStr,playerEmailStr,playerPasswordStr,confirmPassStr="";
    int playerAgeStr;
    DatabaseHelper dh;
    String playerPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        initViews();
        init();
        populate();
        dh  = new DatabaseHelper(this);
    }

    public void initViews(){
        playername = (EditText)findViewById(R.id.playername);
        playeremail = (EditText)findViewById(R.id.playeremail);
        playerage = (EditText)findViewById(R.id.playerage);
        password = (EditText)findViewById(R.id.password);
        confirmpass = (EditText)findViewById(R.id.confirmpass);
        positionSpinner = findViewById(R.id.spinner);
        register = (Button)findViewById(R.id.buttonRegisterPlayer);
    }



    public void populate(){
        positionSpinner.setItems(getResources().getStringArray(R.array.position_array));
    }

    public void init()
    {
        positionSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                playerPosition = item.toString();

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerNameStr = playername.getText().toString();
                playerAgeStr = Integer.parseInt(playerage.getText().toString());
                playerEmailStr = playeremail.getText().toString();
                playerPasswordStr = password.getText().toString();
                confirmPassStr = confirmpass.getText().toString();

                if (!playerPasswordStr.equals(confirmPassStr)) {
                    Toast.makeText(PlayerRegisterActivity.this, "Passwords dont match", Toast.LENGTH_LONG).show();
                } else {
                    boolean isinserted = dh.insertdata(playerNameStr, playerEmailStr, playerAgeStr, playerPasswordStr, playerPosition);

                    if (isinserted == true)
                        Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_SHORT).show();

                    else
                        Toast.makeText(getApplicationContext(), "Register Unsuccessful", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    public void goToPlayerLogin(View view) {
        onBackPressed();
    }
}
