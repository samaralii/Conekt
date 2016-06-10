package com.techiespk.conekt.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import com.techiespk.conekt.R;

/**
 * Created by samar_000 on 6/6/2016.
 */
public class DrawerBaseActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    public Activity activity;
    DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

    protected void onCreateDrawer(Activity activity) {

        this.activity = activity;

        InitializeToolbar();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                getToolbar(),
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
        } else if (id == R.id.nav_payment_methods) {
        } else if (id == R.id.nav_contacts) {
        } else if (id == R.id.nav_transaction_history) {
        } else if (id == R.id.nav_manage_transaction) {
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public ActionBarDrawerToggle getActionbarDrawerToggle() {
        return toggle;
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();

    }
}
