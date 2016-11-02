package com.techiespk.conekt.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.techiespk.conekt.R;
import com.techiespk.conekt.listeners.ChooseAvatarListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by samar_000 on 10/20/2016.
 */

public class ChooseAvatarAdapter extends RecyclerView.Adapter<ChooseAvatarAdapter.ChooseAvatarViewHolder> {

    private Context c;
    private ChooseAvatarListener listener;
    private String url;

    public ChooseAvatarAdapter(Context c, ChooseAvatarListener listener) {
        this.c = c;
        this.listener = listener;
    }


    @Override
    public ChooseAvatarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_avatars, parent, false);
        return new ChooseAvatarViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ChooseAvatarViewHolder holder, int position) {

        ImageView imageView = holder.imageView;

        url = "https://robohash.org/" + position + "?size=95x95";

        Picasso.with(c).load(url).into(imageView);

    }

    @Override
    public int getItemCount() {
        return 50;
    }

    class ChooseAvatarViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageview)
        ImageView imageView;

        public ChooseAvatarViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.imageview)
        void onClick() {
            listener.onAvatarClick("https://robohash.org/" + getLayoutPosition() + "?size=95x95");
        }

    }
}
