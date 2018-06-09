package com.locovox.fazal.foothalls.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.locovox.fazal.foothalls.Models.MD_Event;
import com.locovox.fazal.foothalls.Models.MD_Hall;
import com.locovox.fazal.foothalls.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fazal on 6/4/2018.
 */

public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.ViewHolder> {

    private Context context;
    private List<MD_Event> eventsList;
    private EventsListAdapter.ClickListener clickListner;

    public EventsListAdapter(Context context, List<MD_Event> mdEvents, EventsListAdapter.ClickListener listener) {
        this.context = context;
        this.eventsList = mdEvents;
        this.clickListner = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_event, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.name.setText(eventsList.get(position).getName());
        holder.duration.setText(eventsList.get(position).getTimeInMins());
        holder.capacity.setText(eventsList.get(position).getTotalCapacity());
        holder.date.setText(eventsList.get(position).getDate());

        final MD_Event item = eventsList.get(position);
        holder.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int[] val = {0};
                new MaterialDialog.Builder(context)
                        .title("Register In Event")
                        .inputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL)
                        .inputRange(1,item.totalCapacity)
                        .input("Number Of Players", "", new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                                //val[0] = Integer.parseInt((String) input);
                            }
                        })
                        .positiveText("Register")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                item.totalCapacity = item.totalCapacity - val[0];
                            }
                        })
                        .show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, duration,capacity,date ;
        Button register;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.eventNameDisplay);
            duration = itemView.findViewById(R.id.eventDurationDisplay);
            capacity = itemView.findViewById(R.id.eventCapacityDisplay);
            date = itemView.findViewById(R.id.eventDateDisplay);
            register = itemView.findViewById(R.id.registerEventButton);
        }

        @Override
        public void onClick(View view) {

        }
    }

    public interface ClickListener {

        void onClick();
    }
}
