package com.techiespk.conekt.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.techiespk.conekt.R;
import com.techiespk.conekt.entities.Chat;
import com.techiespk.conekt.entities.User;
import com.techiespk.conekt.listeners.ContactListListener;
import com.techiespk.conekt.ui.activities.ChatActivity;
import com.techiespk.conekt.ui.adapters.ContactsAdapter;
import com.techiespk.conekt.ui.dialogFragment.ContactProfileDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by samar_000 on 7/25/2016.
 */

public class FragmentContacts extends BaseFragment implements ContactListListener {

    private static final String TAG = FragmentContacts.class.getName().toUpperCase();
    public static final String USER = "user";
    private Unbinder unbinder;

    @BindView(R.id.fragment_contacts_contactList)
    RecyclerView contactList;

    @BindView(R.id.fragment_contacts_progressbar)
    ProgressBar progressBar;

    private DatabaseReference myRef;
    private FirebaseUser firebaseUser;
    private ChildEventListener mListener;
    private ArrayList<User> userArrayList = new ArrayList<>();
    private ContactsAdapter adap;

    private User sender;


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

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        myRef = db.getReference("users");
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();






        contactList.hasFixedSize();
        contactList.setLayoutManager(new LinearLayoutManager(getActivity()));


        populateContactList();

    }

    private void populateContactList() {


        progressBar.setVisibility(View.VISIBLE);

        mListener = myRef.limitToFirst(50).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                if (dataSnapshot.exists()) {
                    User u = dataSnapshot.getValue(User.class);
                    String email = u.getEmail();

                    if (!email.equalsIgnoreCase(firebaseUser.getEmail())) {


                        userArrayList.add(dataSnapshot.getValue(User.class));
                        adap = new ContactsAdapter(userArrayList, FragmentContacts.this, getActivity());


                        contactList.setAdapter(adap);

                    } else {

                        sender = dataSnapshot.getValue(User.class);

                    }

                    Log.d(TAG, "Email : " + email);

                }

                progressBar.setVisibility(View.GONE);


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClickContact(int pos) {
        Intent i = new Intent(getActivity(), ChatActivity.class);

        Chat chat = new Chat();
        chat.setReceiver(userArrayList.get(pos));
        chat.setSender(sender);

        i.putExtra(USER, chat);
        startActivity(i);
    }

    @Override
    public void onClickShowProfile(int pos) {
        ContactProfileDialog dialog = new ContactProfileDialog();
        Bundle b = new Bundle();
        b.putParcelable(USER, userArrayList.get(pos));
        dialog.setArguments(b);
        dialog.show(getFragmentManager(), ContactProfileDialog.class.getName());
    }
}
