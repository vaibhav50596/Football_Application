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
import android.widget.Toast;

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
    List<MD_Event> eventAnotherList = new ArrayList<>();
    List<MD_Hall> hallsList = new ArrayList<>();
    Button createEvent;
    DatabaseHelper dh;
    int countEvents = 0;
    String user,userType="", eventAdded;
    int position;
    int eventCapacity = 0;
    MD_Hall hallListModel;

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
        hallsList = dh.retrieveHallData();
        hallListModel = (MD_Hall) getIntent().getSerializableExtra("HallDataModel");
        //MD_Event eventListModel = (MD_Event) getIntent().getSerializableExtra("EventDataModel");
        MD_Event eventListModel = new MD_Event();
        position = (int) getIntent().getIntExtra("position", 0);
        user = getIntent().getStringExtra("user");
        //eventAdded = getIntent().getStringExtra("eventAdd");
        hallListModel = hallsList.get(position);
        hallName.setText(hallListModel.getName());
        hallAddress.setText(hallListModel.getAddress());
        hallCapacity.setText(String.valueOf(hallListModel.getTotalCapacity()));
        hallReview.setText(String.valueOf(hallListModel.getReviewCount()));
        hallRating.setRating(Float.parseFloat(String.valueOf(hallListModel.getRating())));

        if(user.equalsIgnoreCase("Player")){
            createEvent.setVisibility(View.GONE);
        } else {
            createEvent.setVisibility(View.VISIBLE);
        }

        //if(eventList.size() == 0 || eventList == null){
           // eventsMessageWarning.setVisibility(View.VISIBLE);
        //}
        //ArrayList<MD_Event> events = new ArrayList<>();
        eventList = dh.retrieveEventData(hallListModel.getName(), hallListModel.getHallPosition());
        //hallListModel.setEventListInside(eventList);
        //eventListModel = eventList.get(position);

        if(eventList != null && eventList.size() != 0) {
            //setting up GridLayoutManager in events recycler view
            GridLayoutManager gridLayoutManager = new GridLayoutManager(HallDetailActivity.this, 2, GridLayoutManager.VERTICAL, false);
            eventsRecyclerView.setLayoutManager(gridLayoutManager);


            if(eventsRecyclerView.getAdapter() != null){
                countEvents = eventsRecyclerView.getAdapter().getItemCount();
            }
            eventsMessageWarning.setVisibility(View.GONE);
        } else {
            if (eventList.size() == 0 || eventList == null) {
                eventsMessageWarning.setVisibility(View.VISIBLE);
            }
        }

        eventsListAdapter = new EventsListAdapter(this, eventList, HallDetailActivity.this);
        eventsRecyclerView.setAdapter(eventsListAdapter);

        countEvents = eventsRecyclerView.getAdapter().getItemCount();
        /*if(eventAdded != null && eventAdded.equalsIgnoreCase("eventAdded")){
            hallListModel.setCurrentCapacity(hallListModel.getCurrentCapacity() - countEvents);
            hallCapacity.setText(String.valueOf(hallListModel.getCurrentCapacity()));
            dh.updateHallData(hallListModel.getName(),hallListModel.getAddress(),hallListModel.getTotalCapacity(),hallListModel.getReviewCount(),hallListModel.getRating());
        } */

            createEvent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(countEvents < hallListModel.getTotalCapacity()){
                        FragmentManager fm = getFragmentManager();
                        Bundle bundle = new Bundle();
                        bundle.putString("userType", user);
                        bundle.putInt("position", position);
                        bundle.putString("hallName", hallListModel.getName());
                        CreateEventFragment dFragment = new CreateEventFragment();
                        dFragment.setArguments(bundle);
                        // Show DialogFragment and user is able to create event when recycler item count is less than total capacity of halls
                        dFragment.show(fm, "Dialog Fragment");
                    } else {
                        Toast.makeText(HallDetailActivity.this, "You have exceeded limit of hall, please select another hall ", Toast.LENGTH_LONG).show();
                    }

                }
            });


        reviewsListAdapter = new ReviewsListAdapter(this,new ArrayList<MD_Review>());
        reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reviewsRecyclerView.setAdapter(reviewsListAdapter);
    }

    public void populate(){

    }

    @Override
    public void onClick(int position, List<MD_Event> eventUpdatedList) {
        eventList = eventUpdatedList;

        dh.updateEventData(eventList.get(position).getName(), eventList.get(position).getDate(), String.valueOf(eventList.get(position).getTimeInMins()), String.valueOf(eventList.get(position).getTotalCapacity()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        //setting up EventListAdapter in events recycler view
        eventsListAdapter = new EventsListAdapter(this, eventList, HallDetailActivity.this);
        eventsRecyclerView.setAdapter(eventsListAdapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
