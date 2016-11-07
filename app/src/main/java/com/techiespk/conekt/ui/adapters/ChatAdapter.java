package com.techiespk.conekt.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.techiespk.conekt.R;
import com.techiespk.conekt.entities.ChatInfo;
import com.techiespk.conekt.entities.User;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by samar_000 on 8/10/2016.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatAdapterViewHolder> {


    private ArrayList<ChatInfo> data;
    private Context c;
    private User currentUser;

    public ChatAdapter(ArrayList<ChatInfo> data, Context c, User currentUser) {
        this.data = data;
        this.c = c;
        this.currentUser = currentUser;
    }

    @Override
    public ChatAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_chat, parent, false);
        return new ChatAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ChatAdapterViewHolder holder, int position) {


        if (currentUser.getUsername().equalsIgnoreCase(data.get(position).getSenderName())) {

            holder.recieverView.setVisibility(View.GONE);
            holder.senderView.setVisibility(View.VISIBLE);

            holder.StvMessage.setText(data.get(position).getMessage());
            Picasso.with(c).load(data.get(position).getSenderImageUrl()).into(holder.SimageView);

        } else {

            holder.recieverView.setVisibility(View.VISIBLE);
            holder.senderView.setVisibility(View.GONE);

            holder.RtvMessage.setText(data.get(position).getMessage());
            Picasso.with(c).load(data.get(position).getSenderImageUrl()).into(holder.RimageView);

        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ChatAdapterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.list_chat_image)
        ImageView RimageView;

        @BindView(R.id.list_chat_message)
        TextView RtvMessage;

        @BindView(R.id.list_chat_Simage)
        ImageView SimageView;

        @BindView(R.id.list_chat_Smessage)
        TextView StvMessage;

        @BindView(R.id.senderView)
        RelativeLayout senderView;

        @BindView(R.id.recieverView)
        RelativeLayout recieverView;


        ChatAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
