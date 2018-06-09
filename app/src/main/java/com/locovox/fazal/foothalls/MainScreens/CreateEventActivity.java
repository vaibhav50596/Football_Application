package com.locovox.fazal.foothalls.MainScreens;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.locovox.fazal.foothalls.Models.MD_Event;
import com.locovox.fazal.foothalls.Models.MD_Hall;
import com.locovox.fazal.foothalls.R;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CreateEventActivity extends AppCompatActivity {
    EditText eventName, eventDuration, eventCapacity;
    TextView selectedDate;
    Calendar myCalendar = Calendar.getInstance();
    String eventNameStr, eventDurationStr, eventCapacityStr, eventSelectedDateStr;
    Button saveEvent;
    List<MD_Event> eventList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        initViews();
        init();
    }

    public void initViews(){
        eventName = (EditText) findViewById(R.id.eventName);
        eventDuration = (EditText) findViewById(R.id.eventDuration);
        eventCapacity = (EditText) findViewById(R.id.eventCapacity);
        selectedDate = (TextView) findViewById(R.id.selectedDate);
        saveEvent = (Button) findViewById(R.id.saveEvent);
    }

    public void init(){
        final MD_Event eventListModel = new MD_Event();

        selectedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectDate();
            }
        });

        saveEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventNameStr = eventName.getText().toString();
                eventDurationStr = eventDuration.getText().toString();
                eventCapacityStr = eventCapacity.getText().toString();

                if((!eventNameStr.isEmpty() || eventNameStr != null || eventNameStr != "") && (!eventCapacityStr.isEmpty() || eventCapacityStr != null || eventCapacityStr != "")
                   && (!eventDurationStr.isEmpty() || eventDurationStr !=  null || eventDurationStr != "") && (!eventSelectedDateStr.isEmpty() || eventSelectedDateStr != null || eventSelectedDateStr !=""))
                {
                    eventListModel.setName(eventNameStr);
                    eventListModel.setTimeInMins(Integer.parseInt(eventDurationStr));
                    eventListModel.setTotalCapacity(Integer.parseInt(eventCapacityStr));
                    eventListModel.setDate(eventSelectedDateStr);
                    Intent intent = new Intent(CreateEventActivity.this , HallDetailActivity.class);
                    intent.putExtra("EventDataModel", eventListModel);
                    startActivity(intent);
                } else {
                    Toast.makeText(CreateEventActivity.this, "Please fill all details", Toast.LENGTH_LONG).show();
                    //Snackbar snackbar = Snackbar.make(view, "Please fill all details", Snackbar.LENGTH_LONG);
                    //snackbar.show();
                }

            }
        });
    }


    public void selectDate() {
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDate();
            }
        };

        new DatePickerDialog(CreateEventActivity.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateDate() {
        String myFormat = "MM/dd/yyyy"; //date format
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        selectedDate.setText(sdf.format(myCalendar.getTime()));
        eventSelectedDateStr = selectedDate.getText().toString();
    }

}
