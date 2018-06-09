package com.locovox.fazal.foothalls.LoginScreens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.locovox.fazal.foothalls.MainScreens.PlayerHomeActivity;
import com.locovox.fazal.foothalls.R;
import com.locovox.fazal.foothalls.RegisterScreens.PlayerRegisterActivity;

public class PlayerLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_login);
    }

    public void goToPlayerRegister(View view) {
        startActivity(new Intent(PlayerLoginActivity.this, PlayerRegisterActivity.class));
    }

    public void loginPlayer(View view) {
        startActivity(new Intent(PlayerLoginActivity.this, PlayerHomeActivity.class));
    }
}
