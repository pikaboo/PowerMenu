package com.skydoves.powermenudemo;

import android.content.Context;
import android.view.View;
import android.widget.Toast;


import com.skydoves.powermenu.OnShowListener;
import com.skydoves.powermenu.PopupMenu;
import com.skydoves.powermenu.InitView;
import com.skydoves.powermenu.MenuAnimation;
import com.skydoves.powermenu.OnDismissedListener;
import com.skydoves.powermenu.PopupMenuBuilder;
import com.skydoves.powermenudemo.customs.adapters.CenterMenuAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Developed by skydoves on 2017-10-29.
 * Copyright (c) 2017 skydoves rights reserved.
 */

public class MenuFactory {

    public static PopupMenu createHamburgerMenu(Context context, LifecycleOwner lifecycleOwner, OnDismissedListener onDismissedListener) {
        return new PopupMenuBuilder()
                .setAutoDismiss(true)
                .setLifecycleOwner(lifecycleOwner)
                .setAnimation(MenuAnimation.SHOWUP_TOP_LEFT)
                .setMenuRadius(10f)
                .setMenuShadow(10f)
                .setHeight(300)
                .setOnDismissListener(onDismissedListener)
                .setContentView(R.layout.list, new InitView() {
                    @Override
                    public void onViewCreated(PopupMenu menu, View view) {
                        RecyclerView list = view.findViewById(R.id.list);
                        list.setLayoutManager(new LinearLayoutManager(list.getContext()));
                        List<String> items = new ArrayList<>();
                        items.add("one");
                        items.add("two");
                        items.add("three");
                        items.add("four");
                        list.setAdapter(new CenterMenuAdapter(items));
                    }
                })
                .build(context);
    }

    public static PopupMenu createProfileMenu(Context context, LifecycleOwner lifecycleOwner) {
        return new PopupMenuBuilder()
                .setLifecycleOwner(lifecycleOwner)
                .setAnimation(MenuAnimation.SHOWUP_TOP_RIGHT)
                .setMenuRadius(10f)
                .setMenuShadow(10f)
                .build(context);
    }


    public static PopupMenu createShareMenu(Context context, LifecycleOwner lifecycleOwner){
        return new PopupMenuBuilder()
                .setLifecycleOwner(lifecycleOwner)
                .setAnimation(MenuAnimation.FADE)
                .setMenuRadius(10f)
                .setMenuShadow(10f)
                .setContentView(R.layout.share_dialog, new InitView() {
                    @Override
                    public void onViewCreated(final PopupMenu menu, View view) {
                        view.findViewById(R.id.textView_yes).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(v.getContext(),"Clicked YES",Toast.LENGTH_LONG).show();
                                menu.dismiss();
                            }
                        });

                        view.findViewById(R.id.textView_no).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(v.getContext(),"Clicked NO",Toast.LENGTH_LONG).show();
                                menu.dismiss();
                            }
                        });
                    }
                })
                .build(context);
    }
    public static PopupMenu createIconMenu(Context context, LifecycleOwner lifecycleOwner, OnShowListener onShowListener) {
        return new PopupMenuBuilder()
                .setLifecycleOwner(lifecycleOwner)
                .setAnimation(MenuAnimation.FADE)
                .setMenuRadius(10f)
                .setMenuShadow(10f)
                .setContentView(R.layout.share_dialog, new InitView() {
                    @Override
                    public void onViewCreated(final PopupMenu menu, View view) {
                        view.findViewById(R.id.textView_yes).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(v.getContext(),"Clicked YES",Toast.LENGTH_LONG).show();
                                menu.dismiss();
                            }
                        });

                        view.findViewById(R.id.textView_no).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(v.getContext(),"Clicked NO",Toast.LENGTH_LONG).show();
                                menu.dismiss();
                            }
                        });
                    }
                })
                .setOnShowListener(onShowListener)
                .build(context);
    }

    public static PopupMenu createDialogMenu(Context context, LifecycleOwner lifecycleOwner) {
        return new PopupMenuBuilder()
                .setLifecycleOwner(lifecycleOwner)
                .setAnimation(MenuAnimation.SHOW_UP_CENTER)
                .setMenuRadius(10f)
                .setMenuShadow(10f)
                .setWidth(600)
                .build(context);
    }

}
