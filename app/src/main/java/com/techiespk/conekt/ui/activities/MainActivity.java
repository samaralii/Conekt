package com.techiespk.conekt.ui.activities;

import android.os.Bundle;

import com.techiespk.conekt.R;
import com.techiespk.conekt.ui.fragments.FragmentMain;
import com.techiespk.conekt.ui.fragments.LoginFragment;
import com.techiespk.conekt.ui.fragments.RegistrationFragment;

import butterknife.ButterKnife;

/**
 * Created by samar_000 on 6/6/2016.
 */
public class MainActivity extends BaseActivity implements FragmentMain.Listeners{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initComponents();
    }

    private void initComponents() {
        openFragment(R.id.activity_main_container, new FragmentMain());

    }

    @Override
    public void onLoginClick() {
        openFragment(R.id.activity_main_container, new LoginFragment());

    }

    @Override
    public void onRegClick() {
        openFragment(R.id.activity_main_container, new RegistrationFragment());
    }


}
