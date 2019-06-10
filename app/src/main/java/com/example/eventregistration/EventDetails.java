package com.example.eventregistration;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EventDetails extends AppCompatActivity {

    int imageId;
    String name,date,time,details;

    FirebaseAuth auth;
    TextView eventName,eventTime,eventDate,eventDetails;
    ImageView imageView;
    Button btnRegister;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        firebaseDatabase=FirebaseDatabase.getInstance();

        auth=FirebaseAuth.getInstance();
        databaseReference = firebaseDatabase.getReference("reg").child("Applications");

        eventName =findViewById(R.id.subEventName);
        eventDate =findViewById(R.id.eventDate);
        eventTime =findViewById(R.id.eventTime);
        eventDetails =findViewById(R.id.eventDetails);
        imageView =findViewById(R.id.subEventImage);
        btnRegister =findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              registerTeam();
            }
        });

        if (getIntent() != null){

            //get data from intent...
            imageId =getIntent().getIntExtra("image_id",-1);
            name =getIntent().getStringExtra("name");
            date =getIntent().getStringExtra("date");
            time =getIntent().getStringExtra("time");
            details =getIntent().getStringExtra("details");

            //setting data...
            imageView.setImageResource(imageId);
            eventName.setText(name);
            eventDate.setText("Date: "+date);
            eventTime.setText("Time: "+time);
            eventDetails.setText("Details: "+details);
        }
    }

    private void registerTeam(){
        AlertDialog.Builder alertDialog =new AlertDialog.Builder(EventDetails.this);
        alertDialog.setTitle("REGISTER");

        LayoutInflater layoutInflater =this.getLayoutInflater();
        View regLayout = layoutInflater.inflate(R.layout.register_layout,null);

        final TextView regEventName =regLayout.findViewById(R.id.regEventName);
        final EditText leaderName =regLayout.findViewById(R.id.teamLeaderName);
        final EditText regPhone =regLayout.findViewById(R.id.regPhone);
        Button regBtn =regLayout.findViewById(R.id.regBtn);

        regEventName.setText(name);

        alertDialog.setView(regLayout);
        final AlertDialog dialog=alertDialog.create();

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

                if (TextUtils.isEmpty(leaderName.getText().toString())){
                    Toast.makeText(EventDetails.this, "This field can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(regPhone.getText().toString())){
                    Toast.makeText(EventDetails.this, "This field can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                saveData();
                Toast.makeText(EventDetails.this, "Done", Toast.LENGTH_SHORT).show();

            }

            private void saveData() {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(EventDetails.this);
                builder1.setMessage("Please Wait!");
                String personName= leaderName.getText().toString().trim();
                String phoneNo =regPhone.getText().toString();

                final DatabaseReference databaseReference1 =databaseReference.push();
                final Map teamMap = new HashMap();

                teamMap.put("eventName",name);
                teamMap.put("teamName",personName);
                teamMap.put("phone",phoneNo);

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        databaseReference1.setValue(teamMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()){
                                    Toast.makeText(EventDetails.this, "Registration Completed", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(EventDetails.this, "Error "+task.getException().
                                            getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                thread.start();

            }
        });
        dialog.show();
    }

}
