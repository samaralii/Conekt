package com.techiespk.conekt.ui.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.techiespk.conekt.R;

/**
 * Created by samar_000 on 6/6/2016.
 */
public class BaseActivity extends AppCompatActivity {

    protected Toolbar toolbar;
    private ActionBar actionBar;
    protected FragmentTransaction fragmentTransaction;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

        //initializing important things here
       InitializeToolbar();

    }
    void InitializeToolbar() {
        toolbar = (Toolbar) findViewById(R.id.app_toolbar);

        if (toolbar != null)
            setSupportActionBar(toolbar);
    }


    protected void openFragment(@IdRes int resId, Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(resId, fragment);
        ft.commit();
    }

    public ActionBar getActionB() {
        return actionBar;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }
}



