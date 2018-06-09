package com.locovox.fazal.foothalls.MainScreens;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.locovox.fazal.foothalls.Adapters.EventsListAdapter;
import com.locovox.fazal.foothalls.Adapters.ReviewsListAdapter;
import com.locovox.fazal.foothalls.Models.MD_Event;
import com.locovox.fazal.foothalls.Models.MD_Hall;
import com.locovox.fazal.foothalls.Models.MD_Review;
import com.locovox.fazal.foothalls.R;
import com.locovox.fazal.foothalls.SQLite.DatabaseHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HallDetailActivity extends FragmentActivity implements EventsListAdapter.ClickListener {

    RecyclerView eventsRecyclerView;
    RecyclerView reviewsRecyclerView;
    EventsListAdapter eventsListAdapter;
    ReviewsListAdapter reviewsListAdapter;
    TextView hallName, hallAddress, hallCapacity, hallReview, eventsMessageWarning;
    RatingBar hallRating;
    List<MD_Event> eventList = new ArrayList<>();
    Button createEvent;
    DatabaseHelper dh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_detail);
        initViews();
        dh  = new DatabaseHelper(this);
        init();
        populate();
    }

    public void initViews(){
        hallName = (TextView) findViewById(R.id.hallNameDetails);
        hallAddress = (TextView) findViewById(R.id.hallAddressDetails);
        hallCapacity = (TextView) findViewById(R.id.hallCapacityDetails);
        hallReview = (TextView) findViewById(R.id.hallReviewsDetails);
        hallRating = (RatingBar) findViewById(R.id.hallRatingDetails);
        eventsRecyclerView = (RecyclerView) findViewById(R.id.eventsRecycler);
        reviewsRecyclerView = (RecyclerView) findViewById(R.id.reviewsRecycler);
        eventsMessageWarning = (TextView) findViewById(R.id.eventsMessage);
        createEvent = (Button) findViewById(R.id.createEventButton);
    }

    public void init(){
        MD_Hall hallListModel = (MD_Hall) getIntent().getSerializableExtra("HallDataModel");
        MD_Event eventListModel = (MD_Event) getIntent().getSerializableExtra("EventDataModel");

        hallName.setText(hallListModel.getName());
        hallAddress.setText(hallListModel.getAddress());
        hallCapacity.setText(String.valueOf(hallListModel.getTotalCapacity()));
        hallReview.setText(String.valueOf(hallListModel.getReviewCount()));
        hallRating.setRating(Float.parseFloat(String.valueOf(hallListModel.getRating())));

        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                CreateEventFragment dFragment = new CreateEventFragment();
                // Show DialogFragment
                dFragment.show(fm, "Dialog Fragment");

                //Intent intent = new Intent(HallDetailActivity.this , CreateEventFragment.class);
                //intent.putExtra("EventDataModel", (Serializable) eventList);
                //startActivity(intent);
            }
        });

        //if(eventList.size() == 0 || eventList == null){
           // eventsMessageWarning.setVisibility(View.VISIBLE);
        //}
        //ArrayList<MD_Event> events = new ArrayList<>();

        if(eventListModel != null) {

            for (int i = 0; i < hallListModel.getTotalCapacity(); i++) {
                eventList.add(eventListModel);
            }

            //setting up GridLayoutManager in events recycler view
            GridLayoutManager gridLayoutManager = new GridLayoutManager(HallDetailActivity.this, 2, GridLayoutManager.VERTICAL, false);
            eventsRecyclerView.setLayoutManager(gridLayoutManager);

            //setting up EventListAdapter in events recycler view
            eventsListAdapter = new EventsListAdapter(this, eventList, HallDetailActivity.this);
            eventsRecyclerView.setAdapter(eventsListAdapter);
        } else {
            if(eventList.size() == 0 || eventList == null){
                eventsMessageWarning.setVisibility(View.VISIBLE);
            }
        }

        reviewsListAdapter = new ReviewsListAdapter(this,new ArrayList<MD_Review>());
        reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reviewsRecyclerView.setAdapter(reviewsListAdapter);
    }

    public void populate(){

    }

    @Override
    public void onClick() {
        //Intent intent = new Intent(PlayerHomeActivity.this , HallDetailActivity.class);
        //intent.putExtra("HallDataModel", model);
        //startActivity(intent);
    }
}
