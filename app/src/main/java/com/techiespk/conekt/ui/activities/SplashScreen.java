package com.techiespk.conekt.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.techiespk.conekt.R;

import butterknife.ButterKnife;

/**
 * Created by samar_000 on 6/6/2016.
 */
public class SplashScreen extends BaseActivity {

    private static final long DELAY_DURATION = 1000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        ButterKnife.bind(this);
        initComponents();
    }

    private void initComponents() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();
            }
        }, DELAY_DURATION);
    }
}
