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

import com.example.eventregistration.CulturalEvent;
import com.example.eventregistration.Interface.ItemClickListener;
import com.example.eventregistration.MainActivity;
import com.example.eventregistration.Model.EventList;
import com.example.eventregistration.R;
import com.example.eventregistration.SportsEvent;
import com.example.eventregistration.TechEvent;

import java.util.List;

class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public ImageView imageView;
    public TextView textView;
    private ItemClickListener itemClickListener;

    public RecyclerViewHolder(@NonNull View itemView) {
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

public class RecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerViewHolder>{

    private List<EventList>eventLists;
    private Context context;

    public RecyclerViewAdapter(List<EventList> eventLists, Context context) {
        this.eventLists = eventLists;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_eventlist,viewGroup,false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {



        recyclerViewHolder.imageView.setImageResource(eventLists.get(i).getImageId());
        recyclerViewHolder.textView.setText(eventLists.get(i).getEventName());

        recyclerViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                //call new Activity
                final Intent intent;
                switch (position){
                case 0:
                        intent =  new Intent(context, SportsEvent.class);
                        break;

                    case 1:
                        intent =  new Intent(context, CulturalEvent.class);
                        break;
                    case 2:
                        intent =  new Intent(context, TechEvent.class);
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
