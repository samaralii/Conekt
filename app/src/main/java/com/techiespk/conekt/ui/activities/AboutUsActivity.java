package com.techiespk.conekt.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.techiespk.conekt.R;

import butterknife.ButterKnife;

/**
 * Created by samar_000 on 11/10/2016.
 */

public class AboutUsActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutus_activity);
        ButterKnife.bind(this);
    }
}
