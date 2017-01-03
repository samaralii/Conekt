package com.techiespk.conekt.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.squareup.picasso.Picasso;
import com.techiespk.conekt.R;
import com.techiespk.conekt.Utilz;
import com.techiespk.conekt.ui.fragmentDialogs.ChangeEmailDialog;
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
//    @BindView(R.id.activiy_profile_email)
//    EditText etEmail;


    private String name;
    private String email;
    private Uri photoUrl;
    private String imageUrl;
    private String TAG = ProfileActivity.class.getName().toUpperCase();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        initComponents();
    }

    private void initComponents() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            name = user.getDisplayName();
            email = user.getEmail();
            photoUrl = user.getPhotoUrl();
            imageUrl = user.getPhotoUrl().toString();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();

            Picasso.with(this).load(user.getPhotoUrl()).into(profileImage);
//            etEmail.setText(user.getEmail());
            etUsername.setText(user.getDisplayName());
        }
    }

//
//    @OnClick(R.id.activity_profile_changeEmail)
//    void onEmailChangedClick() {
//        ChangeEmailDialog dialog = new ChangeEmailDialog();
//        dialog.show(getSupportFragmentManager(), null);
//    }



    @OnClick(R.id.activity_profile_btnChangePassword)
    void OnChangePasswordClick() {
        ChangePasswordDialog dialog = new ChangePasswordDialog();
        dialog.show(getSupportFragmentManager(), null);

    }


    @OnClick(R.id.activity_profile_changeAvatar)
    void onAvatarChange() {
        startActivityForResult(new Intent(this, ChooseAvatarActivity.class), 1);
    }


    @OnClick(R.id.activity_profile_changeUsername)
    void onChangeUserName() {
            updateUsernameAndImage();
    }

    private void updateUsernameAndImage() {

        if (etUsername.getText().toString().isEmpty()) {
            etUsername.setError("Enter Username");
            return;
        } else {
            etUsername.setError(null);
        }

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(etUsername.getText().toString().trim())
                .setPhotoUri(Uri.parse(imageUrl))
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Utilz.tmsg(ProfileActivity.this, "Updated.");
                            Log.d(TAG, "User profile updated.");
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK)
            return;

        if (requestCode == 1) {
            String url = data.getExtras().getString(URL);
            imageUrl = url;
            profileImage.setImageResource(0);
            Picasso.with(this).load(url).into(profileImage);
            updateUsernameAndImage();
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

        return false;
    }


}
