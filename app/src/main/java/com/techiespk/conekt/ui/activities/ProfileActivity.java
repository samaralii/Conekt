package com.techiespk.conekt.ui.activities;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.techiespk.conekt.R;
import com.techiespk.conekt.Utilz;
import com.techiespk.conekt.fragmentDialogs.ChangePasswordDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by samar_000 on 7/26/2016.
 */

public class ProfileActivity extends BaseActivity {


    @BindView(R.id.activity_profile_profile_image)
    CircleImageView profileImage;
    @BindView(R.id.activity_profile_username)
    EditText etUsername;
    @BindView(R.id.activiy_profile_email)
    EditText etEmail;
    @BindView(R.id.activity_profile_edit)
    ImageView editProfile;
    @BindView(R.id.activity_profile_done)
    ImageView doneProfile;
    @BindView(R.id.activity_profile_btnChangePassword)
    Button btnChangePassword;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        initComponents();
    }

    private void initComponents() {

    }


    @OnClick(R.id.activity_profile_edit)
    void onEditClick() {
        etEmail.setEnabled(true);
        etUsername.setEnabled(true);
        btnChangePassword.setVisibility(View.VISIBLE);
        editProfile.setVisibility(View.GONE);
        doneProfile.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.activity_profile_done)
    void onDoneClick() {
        etEmail.setEnabled(false);
        etUsername.setEnabled(false);
        btnChangePassword.setVisibility(View.GONE);
        editProfile.setVisibility(View.VISIBLE);
        doneProfile.setVisibility(View.GONE);
    }

    @OnClick(R.id.activity_profile_btnChangePassword)
    void OnChangePasswordClick() {

        ChangePasswordDialog dialog = new ChangePasswordDialog();
        dialog.show(getSupportFragmentManager(), null);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
//
//        if (id == R.id.menu_profile_activity_edit) {
//            EditProfile();
//            return true;
//        } else if (id == R.id.menu_profile_activity_Done) {
//            EditProfileDone();
//            return true;
//        }

        return false;
    }


}
