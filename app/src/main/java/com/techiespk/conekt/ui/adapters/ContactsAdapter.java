package com.techiespk.conekt.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.techiespk.conekt.R;
import com.techiespk.conekt.entities.User;
import com.techiespk.conekt.listeners.ContactListListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by samar_000 on 7/29/2016.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {

    private ArrayList<User> users;
    private ContactListListener listener;
    private Context c;

    public ContactsAdapter(ArrayList<User> users, ContactListListener listener, Context context) {
        this.users = users;
        this.listener = listener;
        this.c = c;
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_contacts, parent, false);
        return new ContactsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, int position) {


        holder.tvUsername.setText(users.get(position).getUsername());
        Picasso.with(c).load(users.get(position).getImageUrl()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class ContactsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        @BindView(R.id.list_contacts_tvUsername)
        TextView tvUsername;
        @BindView(R.id.list_contacts_image)
        ImageView imageView;


        public ContactsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClickContact(getLayoutPosition());
        }

        @OnClick(R.id.list_contacts_tvViewProfile)
        void OnProfileClick() {
            listener.onClickShowProfile(getLayoutPosition());
        }
    }
}
