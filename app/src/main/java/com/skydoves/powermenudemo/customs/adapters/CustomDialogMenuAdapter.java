package com.skydoves.powermenudemo.customs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skydoves.powermenudemo.R;
import com.skydoves.powermenudemo.customs.items.NameCardMenuItem;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Developed by skydoves on 2017-12-18.
 * Copyright (c) 2017 skydoves rights reserved.
 */

public class CustomDialogMenuAdapter extends RecyclerView.Adapter<CustomDialogMenuItemViewHolder> {

    public CustomDialogMenuAdapter() {
        super();
    }


    @NonNull
    @Override
    public CustomDialogMenuItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_name_card, parent, false);
        return new CustomDialogMenuItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomDialogMenuItemViewHolder holder, int position) {
//        NameCardMenuItem item = getItem(position);
//
//        holder.icon.setImageDrawable(item.getIcon());
//        holder.name.setText(item.getName());
//        holder.content.setText(item.getContent());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
