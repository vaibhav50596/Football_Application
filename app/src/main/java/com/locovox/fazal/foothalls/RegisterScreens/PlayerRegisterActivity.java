package com.locovox.fazal.foothalls.RegisterScreens;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.locovox.fazal.foothalls.R;

public class PlayerRegisterActivity extends AppCompatActivity {

    MaterialSpinner positionSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        init();
        populate();
    }

    public void init(){
        positionSpinner = findViewById(R.id.spinner);
    }

    public void populate(){
        positionSpinner.setItems(getResources().getStringArray(R.array.position_array));
    }

    public void registerPlayer(View view) {
    }

    public void goToPlayerLogin(View view) {
        onBackPressed();
    }
}
