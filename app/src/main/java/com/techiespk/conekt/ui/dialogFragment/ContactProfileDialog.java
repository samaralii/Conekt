package com.techiespk.conekt.ui.dialogFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.techiespk.conekt.R;
import com.techiespk.conekt.entities.User;
import com.techiespk.conekt.ui.fragments.FragmentContacts;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by samar_000 on 8/2/2016.
 */

public class ContactProfileDialog extends BaseDialogFragment {

    private User user;

    @BindView(R.id.dialog_profile_profileImage)
    ImageView imageView;

    @BindView(R.id.dialog_profile_tvUsername)
    TextView tvUsername;

    @BindView(R.id.dialog_profile_tvEmail)
    TextView tvEmail;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = getArguments().getParcelable(FragmentContacts.USER);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_fragment_profile, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {

        Picasso.with(getActivity()).load(user.getImageUrl()).into(imageView);

        tvUsername.setText(user.getUsername());
        tvEmail.setText(user.getEmail());
    }
}
