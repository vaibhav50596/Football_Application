package com.locovox.fazal.foothalls.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.locovox.fazal.foothalls.Models.MD_Review;
import com.locovox.fazal.foothalls.R;

import java.util.ArrayList;

/**
 * Created by fazal on 6/4/2018.
 */

public class ReviewsListAdapter extends RecyclerView.Adapter<ReviewsListAdapter.ViewHolder> {

    Context context;
    ArrayList<MD_Review> mdReviews;

    public ReviewsListAdapter(Context context, ArrayList<MD_Review> mdReviews) {
        this.context = context;
        this.mdReviews = mdReviews;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_review, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,review;
        RatingBar rating;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            review = itemView.findViewById(R.id.review);
            rating = itemView.findViewById(R.id.rating);
        }
    }
}
