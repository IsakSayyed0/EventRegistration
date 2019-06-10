package com.example.eventregistration.Adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventregistration.Interface.ItemClickListener;
import com.example.eventregistration.R;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    Context context;
    ArrayList<String> eventNameList;
    ArrayList<String> teamNameList;
    ArrayList<String> phoneNoList;

    @NonNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_reg_row, viewGroup, false);
        return new SearchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SearchAdapter.SearchViewHolder searchViewHolder, int i) {

        searchViewHolder.eventNames.setText(eventNameList.get(i));
        searchViewHolder.teamNames.setText(teamNameList.get(i));
        searchViewHolder.phoneNos.setText(phoneNoList.get(i));
       searchViewHolder.setItemClickListener(new ItemClickListener() {
           @Override
           public void onClick(View view, int position) {
               ClipboardManager   myClipboard = (ClipboardManager) view.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
               ClipData myClip = ClipData.newPlainText("label", searchViewHolder.phoneNos.getText().toString());
               myClipboard.setPrimaryClip(myClip);
               Toast.makeText(view.getContext(), "Phone number copied to clipboard" , Toast.LENGTH_SHORT ).show();
           }
       });
    }

    @Override
    public int getItemCount() {
        return eventNameList.size();
    }

    public SearchAdapter(Context context, ArrayList<String> eventNameList, ArrayList<String> teamNameList, ArrayList<String> phoneNoList) {
        this.context = context;
        this.eventNameList = eventNameList;
        this.teamNameList = teamNameList;
        this.phoneNoList = phoneNoList;
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ItemClickListener itemClickListener;
        TextView eventNames, teamNames,phoneNos;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            eventNames = (TextView) itemView.findViewById(R.id.regEventNames);
            teamNames = (TextView) itemView.findViewById(R.id.regPerson);
            phoneNos = (TextView) itemView.findViewById(R.id.regPhone);
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
