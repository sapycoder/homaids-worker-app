package com.example.jholababa;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList person_id,person_name,person_job,person_phone;
    String id;
    int position;

    CustomAdapter(Activity activity,
                  Context context,
                  ArrayList person_id,
                  ArrayList person_name,
                  ArrayList person_job,
                  ArrayList person_phone){

        this.activity = activity;
        this.context = context;
        this.person_id = person_id;
        this.person_name = person_name;
        this.person_job = person_job;
        this.person_phone=person_phone;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_row,parent,false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        this.position = position;
        holder.person_id_txt.setText(String.valueOf(person_id.get(position)));
        holder.person_name_txt.setText(String.valueOf(person_name.get(position)));
        holder.person_job_txt.setText(String.valueOf(person_job.get(position)));
        holder.person_phone_txt.setText(String.valueOf(person_phone.get(position)));

        //UPDATING INFORMATION

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,UpdateActivity.class);

                intent.putExtra("id", String.valueOf(person_id.get(position)));
                intent.putExtra("name", String.valueOf(person_name.get(position)));
                intent.putExtra("job", String.valueOf(person_job.get(position)));
                intent.putExtra("phone", String.valueOf(person_phone.get(position)));

                activity.startActivityForResult(intent,1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return person_id.size();
    }

     class MyViewHolder extends RecyclerView.ViewHolder{

         ImageView delete;
         TextView person_id_txt,person_name_txt,person_job_txt,person_phone_txt;
         Button update;

         MyViewHolder(@NonNull View itemView) {
            super(itemView);
            person_id_txt = itemView.findViewById(R.id.personRId);
            person_name_txt = itemView.findViewById(R.id.personRname);
            person_job_txt = itemView.findViewById(R.id.personRjob);
            person_phone_txt = itemView.findViewById(R.id.personRphone);
            update = itemView.findViewById(R.id.Rupdate);
        }
    }

}
