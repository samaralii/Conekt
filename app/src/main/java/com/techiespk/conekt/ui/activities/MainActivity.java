package com.techiespk.conekt.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.techiespk.conekt.R;
import com.techiespk.conekt.ui.fragments.LoginFragment;
import com.techiespk.conekt.ui.fragments.MainFragment;
import com.techiespk.conekt.ui.fragments.RegistrationFragment;

import butterknife.ButterKnife;

/**
 * Created by samar_000 on 6/6/2016.
 */
public class MainActivity extends BaseActivity implements MainFragment.Listeners {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initComponents();
    }

    private void initComponents() {
        //Open Main Fragment
        openFragment().replace(R.id.activity_main_container, new MainFragment()).commit();

    }


    @Override
    public void onLoginClick() {
        //Open login fragment
//        openFragment().replace(R.id.activity_main_container, new LoginFragment()).commit();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right,
                R.anim.exit_to_right, R.anim.enter_from_left);
        ft.replace(R.id.activity_main_container, new LoginFragment());
        ft.commit();
    }

    @Override
    public void onRegClick() {
        //Open Reg fragment
        openFragment().replace(R.id.activity_main_container, new RegistrationFragment()).commit();
    }


}
