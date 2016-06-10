package com.techiespk.conekt.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techiespk.conekt.R;
import com.techiespk.conekt.ui.activities.MainActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by samar_000 on 6/7/2016.
 */
public class MainFragment extends BaseFragment {

    private Unbinder unbinder;
    private Listeners listeners;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listeners = (MainActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents();
        return view;
    }

    private void initComponents() {

    }


    @OnClick(R.id.fragment_main_login)
    void onLoginCLick() {
        listeners.onLoginClick();
    }

    @OnClick(R.id.fragment_main_reg)
    void onRegClick() {
        listeners.onRegClick();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public interface Listeners {
        void onLoginClick();

        void onRegClick();
    }
}
