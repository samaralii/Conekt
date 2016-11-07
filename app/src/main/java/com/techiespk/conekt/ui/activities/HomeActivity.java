package com.techiespk.conekt.ui.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.squareup.picasso.Picasso;
import com.techiespk.conekt.R;
import com.techiespk.conekt.ui.fragments.FragmentContacts;

import butterknife.ButterKnife;

/**
 * Created by samar_000 on 6/6/2016.
 */
public class HomeActivity extends BaseActivity {
//
//    @BindView(R.id.viewpager)
//    ViewPager viewPager;
//    @BindView(R.id.tabs)
//    TabLayout tab;
//    @BindView(R.id.nav_view)
//    NavigationView navigationView;

//    private Drawer drawer;

    String name = "";
    String email = "";
    Uri photoUrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initComponents();
        addDrawer();
    }

    private void addDrawer() {

        final PrimaryDrawerItem itemHome = new PrimaryDrawerItem().withIdentifier(1).withName("Home");
        final PrimaryDrawerItem itemLogOut = new PrimaryDrawerItem().withIdentifier(2).withName("Log Out");


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            name = user.getDisplayName();
            email = user.getEmail();
            photoUrl = user.getPhotoUrl();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();
        }


        //initialize and create the image loader logic
        DrawerImageLoader.init(new AbstractDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder) {
                Picasso.with(imageView.getContext()).load(uri).placeholder(placeholder).into(imageView);
            }

            @Override
            public void cancel(ImageView imageView) {
                Picasso.with(imageView.getContext()).cancelRequest(imageView);
            }

    /*
    @Override
    public Drawable placeholder(Context ctx) {
        return super.placeholder(ctx);
    }

    @Override
    public Drawable placeholder(Context ctx, String tag) {
        return super.placeholder(ctx, tag);
    }
    */
        });


        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header_background)
                .addProfiles(
                        new ProfileDrawerItem().withName(name).withEmail(email).withIcon(photoUrl)
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {

                        startActivity(new Intent(HomeActivity.this, ProfileActivity.class));

                        return false;
                    }
                })
                .build();


        new DrawerBuilder()
                .withActivity(this)
                .withToolbar(getToolbar())
                .withDrawerGravity(GravityCompat.START)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        itemHome, itemLogOut
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        if (drawerItem.equals(itemHome)) {
                            startActivity(getIntent());
                            finish();
                        } else if (drawerItem.equals(itemLogOut)) {
                            startActivity(new Intent(HomeActivity.this, MainActivity.class));
                        }

                        return false;
                    }
                })
                .build();
    }

    private void initComponents() {

        openFragment(R.id.activity_home_container, new FragmentContacts());

    }

    private void onProfileClick() {
        startActivity(new Intent(this, ProfileActivity.class));
    }


//    private void setupViewPager(ViewPager viewPager) {
//        Adapter adapter = new Adapter(getSupportFragmentManager());
//        adapter.addFragment(new FragmentContacts(), "Contacts");
//        adapter.addFragment(new FragmentChat(), "Chat");
//
//        viewPager.setAdapter(adapter);
//    }


//    private static class Adapter extends FragmentPagerAdapter {
//        private final List<Fragment> mFragments = new ArrayList<>();
//        private final List<String> mFragmentTitles = new ArrayList<>();
//
//        Adapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        void addFragment(Fragment fragment, String title) {
//            mFragments.add(fragment);
//            mFragmentTitles.add(title);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return mFragments.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return mFragments.size();
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return mFragmentTitles.get(position);
//        }
//    }
}
