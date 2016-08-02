package com.techiespk.conekt.ui.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.techiespk.conekt.R;
import com.techiespk.conekt.ui.activities.HomeActivity;
import com.techiespk.conekt.ui.activities.MainActivity;

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

    }

    @OnClick(R.id.fragment_login_btnLogin)
    void onLoginClick() {
        startActivity(new Intent(getActivity(), HomeActivity.class));
        getActivity().finish();
    }

    @OnClick(R.id.fragment_login_tvBack)
    void onBackClick(){
        openFragment(R.id.activity_main_container, new FragmentMain());
    }


    @OnClick(R.id.fragment_login_forgetPassword)
    void onForgetClick() {
        AlertDialog dialog =  new AlertDialog.Builder(getActivity())
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
