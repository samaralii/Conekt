package com.techiespk.conekt.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import com.techiespk.conekt.R;
import com.techiespk.conekt.ui.fragments.LoginFragment;
import com.techiespk.conekt.ui.fragments.RegistrationFragment;

import butterknife.ButterKnife;

/**
 * Created by samar_000 on 6/6/2016.
 */
public class MainActivity extends BaseActivity implements LoginFragment.Listeners {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initComponents();
    }

    private void initComponents() {
        //Open Main Fragment
        openFragment().replace(R.id.activity_main_container, new LoginFragment()).commit();

    }

    @Override
    public void onLoginClick() {
                startActivity(new Intent(this, HomeActivity.class));

    }

    @Override
    public void onRegClick() {
        openFragment().replace(R.id.activity_main_container, new RegistrationFragment()).commit();
    }




}
