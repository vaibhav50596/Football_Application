package com.locovox.fazal.foothalls.LoginScreens;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.locovox.fazal.foothalls.MainScreens.HallHomeActivity;
import com.locovox.fazal.foothalls.MainScreens.PlayerHomeActivity;
import com.locovox.fazal.foothalls.R;
import com.locovox.fazal.foothalls.RegisterScreens.HallRegisterActivity;
import com.locovox.fazal.foothalls.RegisterScreens.PlayerRegisterActivity;
import com.locovox.fazal.foothalls.SQLite.DatabaseHelper;

public class HallLoginActivity extends AppCompatActivity {
EditText hallEmail,password;
String hallEmailStr,hallPassword;
DatabaseHelper dh;
Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_login);
        dh = new DatabaseHelper(this);
        initviews();
        init();
    }

    public void initviews(){
        hallEmail = (EditText)findViewById(R.id.hallEmailLogin);
        password = (EditText)findViewById(R.id.hallPasswordLogin);
        login= (Button)findViewById(R.id.buttonLoginHall);
    }

    public void init()
    {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hallEmailStr= hallEmail.getText().toString();
                hallPassword = password.getText().toString();
                Cursor res = dh.checkHallLoginDetails(hallEmailStr,hallPassword);
                if(res.getCount()==0)
                {
                    Toast.makeText(getApplicationContext(), "Wrong Email or Password ", Toast.LENGTH_LONG).show();
                    return;
                }

                else
                {
                    Intent intent = new Intent(HallLoginActivity.this , PlayerHomeActivity.class);
                    intent.putExtra("user", "Hall");
                    startActivity(intent);
                }
            }
        });
    }

    public void goToHallRegister(View view) {
        startActivity(new Intent(HallLoginActivity.this, HallRegisterActivity.class));
    }


}
