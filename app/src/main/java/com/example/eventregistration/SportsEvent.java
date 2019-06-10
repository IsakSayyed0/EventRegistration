package com.example.eventregistration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.eventregistration.Adapter.RecyclerViewAdapter;
import com.example.eventregistration.Adapter.SportsRecyclerViewAdapter;
import com.example.eventregistration.Model.EventList;

import java.util.ArrayList;
import java.util.List;

public class SportsEvent extends AppCompatActivity {

    List<EventList> eventLists =new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SportsRecyclerViewAdapter recyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_event);
        setTitle("Sports Events ");

        initData();

        recyclerView =findViewById(R.id.recyclerView);
        recyclerViewAdapter =new SportsRecyclerViewAdapter(eventLists,getBaseContext());
        layoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);

    }

    private void initData() {

        eventLists.add(new EventList(R.drawable.cricket,"Cricket "));
        eventLists.add(new EventList(R.drawable.football,"Football  "));
        eventLists.add(new EventList(R.drawable.chess,"Chess "));

    }
}
