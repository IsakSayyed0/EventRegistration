package com.example.eventregistration;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.eventregistration.Adapter.RegisteredTeamsAdapter;
import com.example.eventregistration.Adapter.TechRecyclerViewAdapter;
import com.example.eventregistration.Model.EventList;
import com.example.eventregistration.Model.Register;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RegisteredTeams extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<Register> eventLists = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RegisteredTeamsAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_teams);

        setTitle("Registered Teams");
        firebaseDatabase =FirebaseDatabase.getInstance();
        databaseReference =firebaseDatabase.getReference("reg").child("Applications");
        recyclerView = findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RegisteredTeamsAdapter(getBaseContext(),eventLists);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //on start get data from fireBase database
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot regSnapshot :dataSnapshot.getChildren()){
                    Register register =regSnapshot.getValue(Register.class);
                    eventLists.add(register);
                }

                recyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
     eventLists.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.register_main_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                Intent intent =new Intent(RegisteredTeams.this,Search.class);
                startActivity(intent);
                break;
            default:
                Toast.makeText(this, "Check", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);

    }
}
