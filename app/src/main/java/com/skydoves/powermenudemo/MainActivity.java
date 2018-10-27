package com.skydoves.powermenudemo;


import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.skydoves.powermenu.OnDismissedListener;
import com.skydoves.powermenu.OnShowListener;
import com.skydoves.powermenu.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Developed by skydoves on 2017-10-29.
 * Copyright (c) 2017 skydoves rights reserved.
 */

public class MainActivity extends AppCompatActivity {

    private PopupMenu hamburgerMenu;
    private PopupMenu profileMenu;
    private PopupMenu dialogMenu;
    private PopupMenu iconMenu;
    private PopupMenu shareMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hamburgerMenu = MenuFactory.createHamburgerMenu(this, this, onHamburgerMenuDismissedListener);
        profileMenu = MenuFactory.createProfileMenu(this, this);
//        writeMenu = MenuFactory.getWritePowerMenu(this, this, onWriteItemClickListener);
//        alertMenu = MenuFactory.getAlertPowerMenu(this, this, onAlertItemClickListener);
//        iconMenu = MenuFactory.createIconMenu(this, this);
        shareMenu = MenuFactory.createShareMenu(this,this);

    }




    private OnDismissedListener onHamburgerMenuDismissedListener = new OnDismissedListener() {
        @Override
        public void onDismissed() {
            Log.d("Test", "onDismissed hamburger menu");
        }
    };





    public void onHamburger(View view) {
        if(hamburgerMenu.isShowing()) {
            hamburgerMenu.dismiss();
            return;
        }
        hamburgerMenu.showAsDropDown(view);
    }

    public void onProfile(View view) {
        if(profileMenu.isShowing()) {
            profileMenu.dismiss();
            return;
        }
        profileMenu.showAsDropDown(view, -370, 0);
    }

    public void onDialog(View view) {
        if(dialogMenu.isShowing()) {
            dialogMenu.dismiss();
            return;
        }
        View layout = findViewById(R.id.layout_main);
        dialogMenu.showAtCenter(layout);
    }



    public void onShare(View view) {
        if(shareMenu.isShowing()) {
            shareMenu.dismiss();
            return;
        }
        shareMenu.showAsDropDown(view, -370, 0);
    }

    @Override
    public void onBackPressed() {
        if(hamburgerMenu.isShowing())
            hamburgerMenu.dismiss();
        else if(profileMenu.isShowing())
            profileMenu.dismiss();
        else if(iconMenu.isShowing())
            iconMenu.dismiss();
        else
            super.onBackPressed();
    }
}
