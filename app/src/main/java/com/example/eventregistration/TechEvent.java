package com.example.eventregistration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.eventregistration.Adapter.SportsRecyclerViewAdapter;
import com.example.eventregistration.Adapter.TechRecyclerViewAdapter;
import com.example.eventregistration.Model.EventList;

import java.util.ArrayList;
import java.util.List;

public class TechEvent extends AppCompatActivity {

    List<EventList> eventLists = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    TechRecyclerViewAdapter recyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tech_event);
        setTitle("Tech Events ");

        initData();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerViewAdapter = new TechRecyclerViewAdapter(eventLists, getBaseContext());
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);

    }

    private void initData() {

        eventLists.add(new EventList(R.drawable.robofight,"Robot Fighting "));
        eventLists.add(new EventList(R.drawable.quiz,"Tech Quiz  "));
        eventLists.add(new EventList(R.drawable.debate,"Tech Debate "));

    }
}