package com.gmail.pavlovsv93.notepadv2.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.StringRes;

public class Note implements Parcelable {

    @StringRes
    private int title;

    @StringRes
    private int text;

    public Note(int title, int text) {
        this.title = title;
        this.text = text;
    }

    protected Note(Parcel in) {
        title = in.readInt();
        text = in.readInt();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public int getTitle() {
        return title;
    }

    public int getText() {
        return text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(title);
        dest.writeInt(text);
    }
}
