package com.techiespk.conekt.ui.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.techiespk.conekt.R;
import com.techiespk.conekt.Utilz;
import com.techiespk.conekt.entities.User;
import com.techiespk.conekt.ui.activities.ChooseAvatarActivity;
import com.techiespk.conekt.ui.activities.ProfileActivity;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

/**
 * Created by samar_000 on 6/6/2016.
 */
public class RegistrationFragment extends BaseFragment {


    private Unbinder unbinder;

    @BindView(R.id.fragment_reg_etEmail)
    TextView etEmail;
    @BindView(R.id.fragment_reg_etPassword)
    TextView etPassword;
    @BindView(R.id.fragment_reg_etRePassword)
    TextView etRePassword;
    @BindView(R.id.fragment_reg_etUsername)
    TextView etUsername;

    @BindView(R.id.activity_reg_image)
    ImageView imageView;

    private boolean isImageSelected = false;


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser firebaseUser;
    private DatabaseReference myRef;
    private static final String TAG = RegistrationFragment.class.getSimpleName().toUpperCase();
    private String url;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @OnClick(R.id.activity_reg_changeAvatar)
    void onAvatarChange() {
        startActivityForResult(new Intent(getActivity(), ChooseAvatarActivity.class), 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK)
            return;

        if (requestCode == 1) {
            url = data.getExtras().getString(ProfileActivity.URL);
            imageView.setImageResource(0);
            Picasso.with(getActivity()).load(url).into(imageView);
            isImageSelected = true;

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents();
        return view;
    }

    private void initComponents() {

        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        authStateListener();

    }

    private void authStateListener() {

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                firebaseUser = firebaseAuth.getCurrentUser();


                if (firebaseUser != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + firebaseUser.getUid());
                } else {
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
    }

    @OnClick(R.id.fragment_reg_btnReg)
    void onRegClick() {


        if (etEmail.getText().toString().trim().isEmpty()) {
            etEmail.setError("Required");
            return;
        } else {
            etPassword.setError(null);
        }


        if (etPassword.getText().toString().trim().isEmpty()) {
            etPassword.setError("Required");
            return;
        } else {
            etPassword.setError(null);
        }

        if (!isImageSelected) {
            Utilz.tmsg(getActivity(), "Please Select Your Avatar");
            return;
        }


        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (!password.equals(etRePassword.getText().toString().trim())) {
            Utilz.tmsg(getActivity(), "Password Are Not Same");
        } else {

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.e(TAG, "onComplete : " + task.isSuccessful());

                            if (task.isSuccessful()) {

                                userDbEntries();
                                openFragment(R.id.activity_main_container, new LoginFragment());
                            } else {
                                Utilz.tmsg(getActivity(), "Something Went Wrong");
                            }

                        }
                    });
        }


    }

    private void userDbEntries() {


        User user = new User();
        user.setEmail(firebaseUser.getEmail());
        user.setUsername(etUsername.getText().toString().trim());
        user.setUid(firebaseUser.getUid());
        user.setImageUrl(url);
        user.setCreatedAt(new Date().getTime());

        myRef.child("users").child(firebaseUser.getUid()).setValue(user);
        myRef.push();


        FirebaseUser userr = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(user.getUsername())
                .setPhotoUri(Uri.parse(user.getImageUrl()))
                .build();

        userr.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User profile updated.");
                        }
                    }

                });
    }


    @OnClick(R.id.fragment_reg_tvBack)
    void onBackClick() {
        openFragment(R.id.activity_main_container, new FragmentMain());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (mAuth != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private boolean validation() {

        if (etEmail.getText().toString().trim().isEmpty() &&
                etPassword.getText().toString().trim().isEmpty() &&
                etRePassword.getText().toString().trim().isEmpty() &&
                etUsername.getText().toString().trim().isEmpty()) {
            Utilz.tmsg(getActivity(), "Fill All Fields");
            return false;
        }

        return true;

    }
}
