package com.techiespk.conekt.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techiespk.conekt.R;
import com.techiespk.conekt.listeners.ContactListListener;
import com.techiespk.conekt.ui.adapters.ContactsAdapter;
import com.techiespk.conekt.ui.dialogFragment.ContactProfileDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by samar_000 on 7/25/2016.
 */

public class FragmentContacts extends BaseFragment implements ContactListListener {

    private Unbinder unbinder;

    @BindView(R.id.fragment_contacts_contactList)
    RecyclerView contactList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents();
        return view;
    }

    private void initComponents() {

        contactList.setLayoutManager(new LinearLayoutManager(getActivity()));
        contactList.setAdapter(new ContactsAdapter(this));
        

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClickContact() {
        ContactProfileDialog dialog = new ContactProfileDialog();
        dialog.show(getFragmentManager(), "Profile_dialog");
    }
}
