package com.techiespk.conekt.ui.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.techiespk.conekt.R;
import com.techiespk.conekt.Utilz;
import com.techiespk.conekt.ui.activities.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by samar_000 on 6/8/2016.
 */
public class LoginFragment extends BaseFragment {


    private Unbinder unbinder;

    @BindView(R.id.fragment_login_etEmail)
    EditText etEmail;
    @BindView(R.id.fragment_login_etPassword)
    EditText etPassword;

    @BindView(R.id.fragment_login_tilEmail)
    TextInputLayout tilEmail;

    @BindView(R.id.fragment_login_tilPassword)
    TextInputLayout tilPassword;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = LoginFragment.class.getSimpleName().toUpperCase();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents();
        return view;
    }

    private void initComponents() {
        mAuth = FirebaseAuth.getInstance();
        authStateListener();
    }


    @OnClick(R.id.fragment_login_tilPassword)
    void onResetPassword() {
        forgetPassword();
    }


    private void forgetPassword() {

        if (etEmail.getText().toString().trim().isEmpty()) {
            etEmail.setError("Please Enter Your Email");
            return;
        } else {
            etEmail.setError(null);
        }


        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress = etEmail.getText().toString().trim();

        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Utilz.tmsg(getActivity(), "Reset Password Email Sent");
                            Log.d(TAG, "Email sent.");
                        } else {
                            Utilz.tmsg(getActivity(), "Failed");
                        }
                    }
                });
    }

    private void authStateListener() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    Log.e(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    Log.e(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
    }

    @OnClick(R.id.fragment_login_btnLogin)
    void onLoginClick() {

        if (!validation())
            return;

        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "Please Wait", "Loading...");

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail", task.getException());
                            Utilz.tmsg(getActivity(), "Authentication failed.");
                        } else {
                            Log.e(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                            startActivity(new Intent(getActivity(), HomeActivity.class));
                            getActivity().finish();
                        }

                        dialog.dismiss();
                    }

                });


    }

    @OnClick(R.id.fragment_login_tvBack)
    void onBackClick() {
        openFragment(R.id.activity_main_container, new FragmentMain());
    }


    @OnClick(R.id.fragment_login_forgetPassword)
    void onForgetClick() {
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle("Password Recovery")
                .setMessage("An email has been sent to your account.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create();

        dialog.show();
    }


    boolean validation() {

        if (etEmail.getText().toString().trim().isEmpty() &&
                etPassword.getText().toString().trim().isEmpty()) {
            Utilz.tmsg(getActivity(), "Fill All Fields");
            return false;
        }

        return true;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
