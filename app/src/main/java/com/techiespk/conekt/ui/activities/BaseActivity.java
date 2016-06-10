package com.techiespk.conekt.ui.activities;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.techiespk.conekt.R;

/**
 * Created by samar_000 on 6/6/2016.
 */
public class BaseActivity extends AppCompatActivity {

    protected Toolbar toolbar;
    protected ActionBar actionBar;
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
    protected void InitializeToolbar() {
        toolbar = (Toolbar) findViewById(R.id.app_toolbar);

        if (toolbar != null)
            setSupportActionBar(toolbar);
    }


    public FragmentTransaction openFragment() {
        return getSupportFragmentManager().beginTransaction();

    }

    public ActionBar getActionB() {
        return actionBar;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }
}



