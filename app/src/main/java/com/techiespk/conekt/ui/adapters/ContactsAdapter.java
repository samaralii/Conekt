package com.techiespk.conekt.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techiespk.conekt.R;
import com.techiespk.conekt.entities.User;
import com.techiespk.conekt.listeners.ContactListListener;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by samar_000 on 7/29/2016.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>{

//    private ArrayList<User> users;
    private ContactListListener listener;

    public ContactsAdapter( ContactListListener listener) {
//        this.users = users;
        this.listener = listener;
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_all_contacts, parent, false);
        return new ContactsViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 100;
    }

    class ContactsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ContactListListener listener;

        public ContactsViewHolder(View itemView, ContactListListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClickContact();
        }
    }
}
