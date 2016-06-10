package com.techiespk.conekt.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;

import com.techiespk.conekt.ui.activities.MainActivity;

/**
 * Created by samar_000 on 6/6/2016.
 */
public class BaseFragment extends Fragment {

    FragmentTransaction fragmentTransaction;
    ActionBar actionBar;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentTransaction = ((MainActivity) getActivity()).openFragment();
        actionBar = ((MainActivity) getActivity()).getActionB();
    }

    FragmentTransaction openFragment() {
        return fragmentTransaction;
    }

    ActionBar getActionB() {
        return actionBar;
    }


}
