package com.example.bucketlist.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bucketlist.DetailActivity;
import com.example.bucketlist.R;
import com.example.bucketlist.model.Event;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventViewHolder> {

    //    Member variables
    private static List<Event> mEvents; // Cached copy of events
    private static Context mContext;

    public EventsAdapter(Context context, List<Event> eventsData) {
        this.mEvents = eventsData;
        this.mContext = context;

//        OnItemSelectedListener listener;
    }


    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EventViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.recyclerview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(EventsAdapter.EventViewHolder holder, int position) {

        Event current = mEvents.get(position);
        // Populate the textviews with data.
        holder.bindTo(current);

        // Covers the case of data not being ready yet.

    }

   public void setEvents(List<Event> events) {
        mEvents = events;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mEvents has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mEvents != null)
            return mEvents.size();
        else return 0;
    }

    static class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        TextView curator;
        TextView price;
        ImageView bannerImg;
//        OnItemSelectedListener listener;

        private EventViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView_title);
            curator = itemView.findViewById(R.id.textView_curator);
            price = itemView.findViewById(R.id.textView_price);
            bannerImg = itemView.findViewById(R.id.imageView_banner);

//            if (mContext instanceof OnItemSelectedListener) {
//                listener = (OnItemSelectedListener) mContext;
//            } else {
//                throw new ClassCastException(mContext.toString()
//                        + " must implement .OnItemSelectedListener");
//            }

//            set OnclickListener to entire view
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {

//            listener.onDetailSelected();
            // TODO: 5/4/2022  implement open detail view

            Event current = mEvents.get(getAdapterPosition());
//            Event current = mEvents.get(position);
            Intent detailIntent = new Intent(mContext, DetailActivity.class);
            detailIntent.putExtra("title", current.getTitle());
            detailIntent.putExtra("curator", current.getCurator());
            detailIntent.putExtra("price", current.getPrice());
            detailIntent.putExtra("image_resource",
                    current.getImageResource());
            mContext.startActivity(detailIntent);
        }

        void bindTo(Event current) {
            // Populate the textViews with data.
            title.setText(current.getTitle());
            curator.setText(current.getCurator());
            price.setText(Integer.toString(current.getPrice()));
            Glide.with(mContext).load(current.getImageResource()).into(bannerImg);
        }


    }

//    public Event getEventAtPosition(int position) {
//        return mEvents.get(position);
//    }
}