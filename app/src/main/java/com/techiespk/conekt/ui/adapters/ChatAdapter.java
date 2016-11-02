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
import com.techiespk.conekt.entities.ChatInfo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by samar_000 on 8/10/2016.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatAdapterViewHolder> {


    private ArrayList<ChatInfo> data;
    private Context c;

    public ChatAdapter(ArrayList<ChatInfo> data, Context c) {
        this.data = data;
        this.c = c;
    }

    @Override
    public ChatAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_chat, parent);
        return new ChatAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ChatAdapterViewHolder holder, int position) {

        int height_in_pixels = holder.tvMessage.getLineCount() * holder.tvMessage.getLineHeight(); //approx height text
        holder.tvMessage.setHeight(height_in_pixels);

        Picasso.with(c).load(data.get(position).getSenderImageUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ChatAdapterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.list_chat_image)
        private ImageView imageView;

        @BindView(R.id.list_chat_message)
        private TextView tvMessage;


        ChatAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
