package com.techiespk.conekt.ui.activities;

import android.os.Bundle;

import com.techiespk.conekt.R;

import butterknife.ButterKnife;

/**
 * Created by samar_000 on 6/6/2016.
 */
public class RegistrationActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
    }
}
