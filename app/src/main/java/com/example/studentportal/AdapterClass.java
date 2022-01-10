package com.example.studentportal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder> {

    List<ModalClass>mList;
    Context context;

    public AdapterClass(List<ModalClass> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layoutfile,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.get().load(mList.get(position).getStd_image()).into(holder.image);
        holder.name.setText("Name: "+mList.get(position).getStd_name());
        holder.id.setText("Id: "+mList.get(position).getStd_id());
        holder.phone.setText("Phone: "+mList.get(position).getStd_phone());
        holder.email.setText("Email: "+mList.get(position).getStd_email());
        holder.bod.setText("University: "+mList.get(position).getStd_bod());
        holder.university.setText("Birth_Date: "+mList.get(position).getStd_university());
        holder.major.setText("Major: "+mList.get(position).getStd_major());
        holder.fathern.setText("Father_Name: "+mList.get(position).getStd_fathern());
        holder.mothern.setText("MotherName: "+mList.get(position).getStd_mothern());
        holder.familym.setText("Family_Members: "+mList.get(position).getStd_familym());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,id,phone,email,bod,major,fathern,mothern,familym,university;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.std_image);
            name=itemView.findViewById(R.id.std_name);
            fathern=itemView.findViewById(R.id.std_fathern);
            mothern=itemView.findViewById(R.id.std_mothern);
            familym=itemView.findViewById(R.id.std_familym);
            id=itemView.findViewById(R.id.std_id);
            email=itemView.findViewById(R.id.std_email);
            phone=itemView.findViewById(R.id.std_phone);
            bod=itemView.findViewById(R.id.std_bodid);
            major = itemView.findViewById(R.id.std_major);
            university=itemView.findViewById(R.id.std_university);

        }
    }
}
