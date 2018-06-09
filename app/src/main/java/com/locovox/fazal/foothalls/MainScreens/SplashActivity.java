package com.locovox.fazal.foothalls.MainScreens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.locovox.fazal.foothalls.LoginScreens.ChooseLoginTypeActivity;
import com.locovox.fazal.foothalls.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    public void goToLogin(View view) {
        startActivity(new Intent(SplashActivity.this,ChooseLoginTypeActivity.class));
    }
}
