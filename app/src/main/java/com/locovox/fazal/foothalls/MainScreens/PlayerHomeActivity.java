package com.locovox.fazal.foothalls.MainScreens;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;


import com.locovox.fazal.foothalls.Adapters.EventsListAdapter;
import com.locovox.fazal.foothalls.Adapters.HallsListAdapter;
import com.locovox.fazal.foothalls.Models.MD_Hall;
import com.locovox.fazal.foothalls.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlayerHomeActivity extends AppCompatActivity implements HallsListAdapter.ClickListener {

    RecyclerView hallsRecyclerView;
    HallsListAdapter hallsListAdapter;
    List<MD_Hall> hallsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_home);
        setTitle("Halls");
        loadHallsData();
        init();
    }

    //This method sets data of hall in model which will be used by HallsListAdapter in order to show in the list format

    public void loadHallsData(){
        String hallNames[]={"Pro Football Hall of Fame","Tom Beck","Matty Bell","Mike Bellotti","Hugo Bezdek","Roy Kidd",
                "Ralph Jordan", "Frank Kush","Andrew Kerr","Bill Ingram"};

        String hallAddresses[]={"490 E South, Orlando, FL", "840 West, Washington DC", "London Place, London", "310 Wall Street, New York",
                "510, Down Town, New Jersey", "366 Idaho Falls, Ohio", "111 Lacinia Avenue, Ohio", "Sunset Valley, Texas",
                "4801 Republic of Texas Blvd, Austin", "Washington Square Park, NY"};

        int hallTotalCapacity[]={12,10,8,6,4,2,3,6,7,5};

        int hallReviews[]={5,10,2,4,5,6,7,8,9,1};

        float ratings[]={2.6f,3f,4f,4.6f,4f,2.6f,3f,4f,4.6f,4f};

        for(int i=0; i<hallNames.length; i++){
            MD_Hall model = new MD_Hall();
            model.setName(hallNames[i]);
            model.setAddress(hallAddresses[i]);
            model.setTotalCapacity(hallTotalCapacity[i]);
            model.setReviewCount(hallReviews[i]);
            model.setRating(ratings[i]);
            hallsList.add(model);
        }

    }

    //setting up LinearLayout Manager and HallsListAdapter in recycler view
    public void init(){
        hallsRecyclerView = findViewById(R.id.hallsRecycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PlayerHomeActivity.this, LinearLayoutManager.VERTICAL, false);
        hallsRecyclerView.setLayoutManager(linearLayoutManager);

        hallsListAdapter = new HallsListAdapter(this, hallsList, PlayerHomeActivity.this);
        hallsRecyclerView.setAdapter(hallsListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.player_home_menu, menu);

        return true;
    }

    public void searchHallsByLocation(MenuItem item) {

    }


    //on click of recylcer view item which opens new Activity
    @Override
    public void onClick(MD_Hall model) {
        Intent intent = new Intent(PlayerHomeActivity.this , HallDetailActivity.class);
        intent.putExtra("HallDataModel", model);
        startActivity(intent);
    }
}
