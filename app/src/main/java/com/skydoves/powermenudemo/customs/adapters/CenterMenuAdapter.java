package com.skydoves.powermenudemo.customs.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skydoves.powermenudemo.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Developed by skydoves on 2017-10-28.
 * Copyright (c) 2017 skydoves rights reserved.
 */

public class CenterMenuAdapter extends RecyclerView.Adapter<CenterMenuItemViewHolder> {

    private final List<String> stringList;

    public CenterMenuAdapter(List<String> items) {
        super();
        stringList = items;
    }

    @NonNull
    @Override
    public CenterMenuItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title_menu, parent, false);
        return new CenterMenuItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CenterMenuItemViewHolder holder, int position) {
        String item = stringList.get(position);
        holder.title.setText(item);
        holder.title.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.md_grey_800));
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }
}
