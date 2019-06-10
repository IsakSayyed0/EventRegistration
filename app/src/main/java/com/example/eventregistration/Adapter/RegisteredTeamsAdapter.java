package com.example.eventregistration.Adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventregistration.Interface.ItemClickListener;
import com.example.eventregistration.MainActivity;
import com.example.eventregistration.Model.Register;
import com.example.eventregistration.R;

import java.util.List;

public class RegisteredTeamsAdapter extends RecyclerView.Adapter<RegisteredTeamsAdapter.MyViewHolder> {

  Context context;
  List<Register>registerList;

    public RegisteredTeamsAdapter(Context context, List<Register> registerList) {
        this.context = context;
        this.registerList = registerList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.layout_reg_row,viewGroup,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {

        myViewHolder.regEventName.setText(registerList.get(i).getEventName());
        myViewHolder.regTeamName.setText(registerList.get(i).getTeamName());
        myViewHolder.regPhone.setText(registerList.get(i).getPhone());

        myViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
             ClipboardManager   myClipboard = (ClipboardManager) view.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
             ClipData myClip = ClipData.newPlainText("label", myViewHolder.regPhone.getText().toString());
             myClipboard.setPrimaryClip(myClip);
             Toast.makeText(view.getContext(), "Phone number copied to clipboard" , Toast.LENGTH_SHORT ).show();
         }
        });
    }

    @Override
    public int getItemCount() {
        return registerList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

        private ItemClickListener itemClickListener;
        TextView regEventName,regPhone,regTeamName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            regEventName =itemView.findViewById(R.id.regEventNames);
            regPhone =itemView.findViewById(R.id.regPhone);
            regTeamName =itemView.findViewById(R.id.regPerson);
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
}
