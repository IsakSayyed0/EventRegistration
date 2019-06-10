package com.example.eventregistration.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventregistration.EventDetails;
import com.example.eventregistration.Interface.ItemClickListener;
import com.example.eventregistration.MainActivity;
import com.example.eventregistration.Model.EventList;
import com.example.eventregistration.R;

import java.util.List;

class TechRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


    public ImageView imageView;
    public TextView textView;
    private ItemClickListener itemClickListener;

    public TechRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView =itemView.findViewById(R.id.eventImage);
        textView =itemView.findViewById(R.id.eventName);
        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition());

    }
}

public class TechRecyclerViewAdapter extends RecyclerView.Adapter<TechRecyclerViewHolder> {

    private List<EventList> eventLists;
    private Context context;

    public TechRecyclerViewAdapter(List<EventList> eventLists, Context context) {
        this.eventLists = eventLists;
        this.context = context;
    }

    @NonNull
    @Override
    public TechRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_eventlist,viewGroup,false);

        return new TechRecyclerViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull TechRecyclerViewHolder techRecyclerViewHolder, int i) {

        techRecyclerViewHolder.imageView.setImageResource(eventLists.get(i).getImageId());
        techRecyclerViewHolder.textView.setText(eventLists.get(i).getEventName());

        techRecyclerViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                final Intent intent;
                switch (position){
                    case 0:
                        intent =  new Intent(context, EventDetails.class);
                        intent.putExtra("image_id",eventLists.get(position).getImageId());
                        intent.putExtra("name",eventLists.get(position).getEventName());
                        intent.putExtra("date","28/4/2019");
                        intent.putExtra("time","4pm");
                        intent.putExtra("details","Team of 8 is required");
                        break;

                    case 1:
                        intent =  new Intent(context, EventDetails.class);
                        intent.putExtra("image_id",eventLists.get(position).getImageId());
                        intent.putExtra("name",eventLists.get(position).getEventName());
                        intent.putExtra("date","28/4/2019");
                        intent.putExtra("time","4pm");
                        intent.putExtra("details","Team of 6 is required");
                        break;

                    case 2:
                        intent =  new Intent(context, EventDetails.class);
                        intent.putExtra("image_id",eventLists.get(position).getImageId());
                        intent.putExtra("name",eventLists.get(position).getEventName());
                        intent.putExtra("date","28/4/2019");
                        intent.putExtra("time","4pm");
                        intent.putExtra("details","Entry fee Rs 100");
                        break;

                    default:
                        intent =  new Intent(context, MainActivity.class);
                        break;
                }
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return eventLists.size();
    }
}
