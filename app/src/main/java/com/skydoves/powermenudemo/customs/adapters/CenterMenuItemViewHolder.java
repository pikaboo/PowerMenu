package com.skydoves.powermenudemo.customs.adapters;

/*
 * Created by Lena Brusilovski on 26/10/2018
 */

import android.view.View;
import android.widget.TextView;

import com.skydoves.powermenudemo.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CenterMenuItemViewHolder extends RecyclerView.ViewHolder {
    final TextView title;

    public CenterMenuItemViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.item_title);

    }
}
