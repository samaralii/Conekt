package com.techiespk.conekt.ui.activities;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.techiespk.conekt.R;

/**
 * Created by samar_000 on 6/6/2016.
 */
public class BaseActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

        //initializing important things here
        if (toolbar == null) {
            toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        }

        setSupportActionBar(toolbar);

    }

    protected void openFragment(@IdRes int id, Fragment fragment, String tag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(id, fragment, tag);
        ft.addToBackStack(null);
        ft.commit();
    }

    protected void openFragment(@IdRes int id, Fragment fragment, String tag, Bundle b) {
        fragment.setArguments(b);
        openFragment(id, fragment, tag);
    }



}
