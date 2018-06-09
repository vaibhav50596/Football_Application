package com.locovox.fazal.foothalls.LoginScreens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.locovox.fazal.foothalls.MainScreens.HallHomeActivity;
import com.locovox.fazal.foothalls.MainScreens.PlayerHomeActivity;
import com.locovox.fazal.foothalls.R;
import com.locovox.fazal.foothalls.RegisterScreens.HallRegisterActivity;
import com.locovox.fazal.foothalls.RegisterScreens.PlayerRegisterActivity;

public class HallLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_login);
    }

    public void goToHallRegister(View view) {
        startActivity(new Intent(HallLoginActivity.this, HallRegisterActivity.class));
    }

    public void loginHall(View view) {
        startActivity(new Intent(HallLoginActivity.this, HallHomeActivity.class));
    }
}
