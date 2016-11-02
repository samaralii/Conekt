package com.techiespk.conekt.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by samar_000 on 11/2/2016.
 */

public class Chat implements Parcelable{

    private User sender;
    private User Receiver;

    public Chat() {

    }

    protected Chat(Parcel in) {
        sender = in.readParcelable(User.class.getClassLoader());
        Receiver = in.readParcelable(User.class.getClassLoader());
    }

    public static final Creator<Chat> CREATOR = new Creator<Chat>() {
        @Override
        public Chat createFromParcel(Parcel in) {
            return new Chat(in);
        }

        @Override
        public Chat[] newArray(int size) {
            return new Chat[size];
        }
    };

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return Receiver;
    }

    public void setReceiver(User receiver) {
        Receiver = receiver;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(sender, flags);
        dest.writeParcelable(Receiver, flags);
    }
}
