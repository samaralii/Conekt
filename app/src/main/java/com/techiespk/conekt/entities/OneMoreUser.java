package com.techiespk.conekt.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by SAMAR on 5/30/2016.
 */

public class OneMoreUser implements Parcelable {

    private long senderCreatedAt;
    private long recipientCreatedAt;

    private String senderEmail;
    private String recipientEmail;

    private String senderUID;
    private String recipientUID;

    private String senderUsername;
    private String recipientUsername;

    OneMoreUser() {}


    protected OneMoreUser(Parcel in) {
        senderCreatedAt = in.readLong();
        recipientCreatedAt = in.readLong();
        senderEmail = in.readString();
        recipientEmail = in.readString();
        senderUID = in.readString();
        recipientUID = in.readString();
        senderUsername = in.readString();
        recipientUsername = in.readString();
    }

    public static final Creator<OneMoreUser> CREATOR = new Creator<OneMoreUser>() {
        @Override
        public OneMoreUser createFromParcel(Parcel in) {
            return new OneMoreUser(in);
        }

        @Override
        public OneMoreUser[] newArray(int size) {
            return new OneMoreUser[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(senderCreatedAt);
        dest.writeLong(recipientCreatedAt);
        dest.writeString(senderEmail);
        dest.writeString(recipientEmail);
        dest.writeString(senderUID);
        dest.writeString(recipientUID);
        dest.writeString(senderUsername);
        dest.writeString(recipientUsername);
    }

    public long getSenderCreatedAt() {
        return senderCreatedAt;
    }

    public void setSenderCreatedAt(long senderCreatedAt) {
        this.senderCreatedAt = senderCreatedAt;
    }

    public long getRecipientCreatedAt() {
        return recipientCreatedAt;
    }

    public void setRecipientCreatedAt(long recipientCreatedAt) {
        this.recipientCreatedAt = recipientCreatedAt;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getSenderUID() {
        return senderUID;
    }

    public void setSenderUID(String senderUID) {
        this.senderUID = senderUID;
    }

    public String getRecipientUID() {
        return recipientUID;
    }

    public void setRecipientUID(String recipientUID) {
        this.recipientUID = recipientUID;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getRecipientUsername() {
        return recipientUsername;
    }

    public void setRecipientUsername(String recipientUsername) {
        this.recipientUsername = recipientUsername;
    }
}
