package com.techiespk.conekt.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.techiespk.conekt.R;
import com.techiespk.conekt.ui.fragmentDialogs.ChangePasswordDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by samar_000 on 7/26/2016.
 */

public class ProfileActivity extends BaseActivity {


    public static final String URL = "imageUrl";
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
    @BindView(R.id.activity_profile_changeAvatar)
    Button btnChangeAvatar;


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
        btnChangeAvatar.setVisibility(View.VISIBLE);
        btnChangePassword.setVisibility(View.VISIBLE);
        editProfile.setVisibility(View.GONE);
        doneProfile.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.activity_profile_done)
    void onDoneClick() {
        etEmail.setEnabled(false);
        etUsername.setEnabled(false);
        btnChangeAvatar.setVisibility(View.GONE);
        btnChangePassword.setVisibility(View.GONE);
        editProfile.setVisibility(View.VISIBLE);
        doneProfile.setVisibility(View.GONE);
    }

    @OnClick(R.id.activity_profile_btnChangePassword)
    void OnChangePasswordClick() {
        ChangePasswordDialog dialog = new ChangePasswordDialog();
        dialog.show(getSupportFragmentManager(), null);

    }


    @OnClick(R.id.activity_profile_changeAvatar)
    void onAvatarChange() {
        startActivityForResult(new Intent(this, ChooseAvatarActivity.class), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK)
            return;

        if (requestCode == 1) {
            String url = data.getExtras().getString(URL);
            profileImage.setImageResource(0);
            Picasso.with(this).load(url).into(profileImage);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

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
