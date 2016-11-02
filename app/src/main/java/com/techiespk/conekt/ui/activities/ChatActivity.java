package com.techiespk.conekt.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.techiespk.conekt.R;
import com.techiespk.conekt.entities.Chat;
import com.techiespk.conekt.entities.ChatInfo;
import com.techiespk.conekt.ui.adapters.ChatAdapter;
import com.techiespk.conekt.ui.fragments.FragmentContacts;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by samar_000 on 8/10/2016.
 */

public class ChatActivity extends BaseActivity {


    @BindView(R.id.activity_chat_messagesList)
    RecyclerView recyclerView;

    @BindView(R.id.activity_chat_typeMessage)
    EditText etMessage;

    @BindView(R.id.activity_chat_send)
    Button btnSend;

    private Chat chat;

    private DatabaseReference myRef;
    private DatabaseReference myChatRef;

    private ChildEventListener mListener;

    ArrayList<ChatInfo> chatInfos;


    private ChatAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        chat = getIntent().getParcelableExtra(FragmentContacts.USER);

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(chat.getReceiver().getUsername());

        chatInfos = new ArrayList<>();

        initComponents();
    }

    private void initComponents() {

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();


        myChatRef = database.getReference("chat");

        adapter = new ChatAdapter(chatInfos, this);
        recyclerView.setAdapter(adapter);


        mListener = myChatRef.limitToFirst(50).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if (dataSnapshot.exists()) {
                    ChatInfo ci = dataSnapshot.getValue(ChatInfo.class);

                    chatInfos.add(ci);


                }

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

    @OnClick(R.id.activity_chat_send)
    void OnSendClick() {
        if (etMessage.getText().toString().isEmpty()) {
            btnSend.setEnabled(false);
            return;
        } else {
            btnSend.setEnabled(true);
        }

        ChatInfo chatInfo = new ChatInfo();

        chatInfo.setMessage(etMessage.getText().toString().trim());
        chatInfo.setReceiverImageUrl(chat.getReceiver().getImageUrl());
        chatInfo.setSenderImageUrl(chat.getSender().getImageUrl());
        chatInfo.setReceiverName(chat.getReceiver().getUsername());
        chatInfo.setSenderName(chat.getSender().getUsername());

        myRef.child("chat").child(getRefName()).setValue(chatInfo);
        myRef.push();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private String getRefName() {
        if (chat.getSender().getCreatedAt() > chat.getReceiver().getCreatedAt()) {
            return chat.getSender().getCreatedAt() + "-" + chat.getReceiver().getCreatedAt();
        } else {
            return chat.getReceiver().getCreatedAt() + "-" + chat.getSender().getCreatedAt();
        }
    }
}
