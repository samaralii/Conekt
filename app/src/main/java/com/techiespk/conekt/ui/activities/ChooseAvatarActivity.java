package com.techiespk.conekt.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.techiespk.conekt.R;
import com.techiespk.conekt.listeners.ChooseAvatarListener;
import com.techiespk.conekt.ui.adapters.ChooseAvatarAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by samar_000 on 10/20/2016.
 */

public class ChooseAvatarActivity extends AppCompatActivity {

    @BindView(R.id.list_avatar)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_avatar);
        ButterKnife.bind(this);
        init();
    }

    ChooseAvatarListener listener = new ChooseAvatarListener() {
        @Override
        public void onAvatarClick(String Url) {
            Intent i = new Intent();
            i.putExtra(ProfileActivity.URL, Url);
            setResult(RESULT_OK, i);
            finish();
        }
    };

    private void init() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.hasFixedSize();

        ChooseAvatarAdapter adapter = new ChooseAvatarAdapter(this, listener);
        recyclerView.setAdapter(adapter);
    }
}
