package com.example.eventregistration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.eventregistration.Adapter.CulturalRecyclerView;
import com.example.eventregistration.Adapter.SportsRecyclerViewAdapter;
import com.example.eventregistration.Model.EventList;

import java.util.ArrayList;
import java.util.List;

public class CulturalEvent extends AppCompatActivity {
    List<EventList> eventLists =new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CulturalRecyclerView recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultral_event);
        setTitle("Cultural Events ");

        initData();

        recyclerView =findViewById(R.id.recyclerView);
        recyclerViewAdapter =new CulturalRecyclerView(eventLists,getBaseContext());
        layoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void initData() {

        eventLists.add(new EventList(R.drawable.fashionshow,"Fashion Show "));
        eventLists.add(new EventList(R.drawable.dance,"Dance  "));
        eventLists.add(new EventList(R.drawable.singing,"Singing "));

    }
}
