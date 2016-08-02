package com.techiespk.conekt.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.techiespk.conekt.R;
import com.techiespk.conekt.ui.fragments.FragmentChat;
import com.techiespk.conekt.ui.fragments.FragmentContacts;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by samar_000 on 6/6/2016.
 */
public class HomeActivity extends DrawerBaseActivity {

    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tabs)
    TabLayout tab;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        super.onCreateDrawer(this);
        initComponents();
    }

    private void initComponents() {
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        tab.setupWithViewPager(viewPager);


        View header = navigationView.getHeaderView(0);

        final TextView email = (TextView) navigationView.getHeaderView(0).getRootView().findViewById(R.id.nav_header_main2_email);


        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onProfileClick();
                email.setText("samarali@live.com");
            }
        });


    }

    private void onProfileClick() {
        startActivity(new Intent(this, ProfileActivity.class));
    }


    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentContacts(), "Contacts");
        adapter.addFragment(new FragmentChat(), "Chat");

        viewPager.setAdapter(adapter);
    }




    private static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        Adapter(FragmentManager fm) {
            super(fm);
        }

        void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
