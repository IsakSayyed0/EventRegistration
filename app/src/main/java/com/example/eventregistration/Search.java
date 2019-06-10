package com.example.eventregistration;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eventregistration.Adapter.SearchAdapter;
import com.example.eventregistration.Model.Register;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Search extends AppCompatActivity {
    EditText search_edit_text;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    ArrayList<String> eventNameList;
    ArrayList<String> teamNameList;
    ArrayList<String> phoneNoList;
    int counter = 0;
    SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        search_edit_text = (EditText) findViewById(R.id.search_edit_text);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        databaseReference = FirebaseDatabase.getInstance().getReference("reg").child("Applications");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        eventNameList = new ArrayList<>();
        phoneNoList = new ArrayList<>();
        teamNameList = new ArrayList<>();

        search_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!s.toString().isEmpty()) {
                   setAdapter(s.toString());
                } else {
                    /*
                     * Clear the list when editText is empty
                     * */
                    eventNameList.clear();
                    phoneNoList.clear();
                    teamNameList.clear();
                    recyclerView.removeAllViews();
                }
            }
        });
    }

    private void setAdapter(final String searchedString) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                /*
                 * Clear the list for every new search
                 * */
                eventNameList.clear();
                phoneNoList.clear();
                teamNameList.clear();
                recyclerView.removeAllViews();
                /*
                 * Search all users for matching searched string
                 * */
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String uid = snapshot.getKey();
                    String eventName = snapshot.child("eventName").getValue(String.class);
                    String teamName = snapshot.child("teamName").getValue(String.class);
                    String phoneNo = snapshot.child("phone").getValue(String.class);

                        if (eventName.toLowerCase().contains(searchedString.toLowerCase())) {
                            eventNameList.add(eventName);
                            teamNameList.add(teamName);
                            phoneNoList.add(phoneNo);
                            counter++;
                        } else if (teamName.toLowerCase().contains(searchedString.toLowerCase())) {
                            eventNameList.add(eventName);
                            teamNameList.add(teamName);
                            phoneNoList.add(phoneNo);
                            counter++;
                        }
                    /*
                     * Get maximum of 15 searched results only
                     * */
                    if (counter == 15)
                        break;
                }
                searchAdapter = new SearchAdapter(Search.this, eventNameList, teamNameList, phoneNoList);
                recyclerView.setAdapter(searchAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
