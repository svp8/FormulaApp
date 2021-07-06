package com.example.formulaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class PageAdapter extends RecyclerView.Adapter<PageAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<itemPage> arrayList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView4);
        }
    }

    public PageAdapter(Context context, ArrayList<itemPage> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.page_item, parent, false);
        return new PageAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        itemPage itemPage = arrayList.get(position);
        holder.imageView.setImageResource(itemPage.getImageResource());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
