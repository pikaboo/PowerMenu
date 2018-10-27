package com.skydoves.powermenudemo.customs.adapters;

/*
 * Created by Lena Brusilovski on 26/10/2018
 */


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.skydoves.powermenudemo.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomDialogMenuItemViewHolder extends RecyclerView.ViewHolder {

    protected final ImageView icon;
    protected final TextView name;
    protected final TextView content;

    public CustomDialogMenuItemViewHolder(@NonNull View itemView) {
        super(itemView);

        icon = itemView.findViewById(R.id.item_name_card_profile);
        name = itemView.findViewById(R.id.item_name_card_name);
        content = itemView.findViewById(R.id.item_name_card_board);
    }
}
