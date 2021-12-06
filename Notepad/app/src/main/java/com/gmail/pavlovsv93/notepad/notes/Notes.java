package com.gmail.pavlovsv93.notepad.notes;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

public class Notes implements Parcelable {

    @StringRes
    private int title;

    @StringRes
    private int textNote;

    public Notes(int title, int image) {
        this.title = title;
        this.textNote = image;
    }

    protected Notes(Parcel in) {

        title = in.readInt();
        textNote = in.readInt();
    }

    public static final Creator<Notes> CREATOR = new Creator<Notes>() {
        @Override
        public Notes createFromParcel(Parcel in) {
            return new Notes(in);
        }

        @Override
        public Notes[] newArray(int size) {
            return new Notes[size];
        }
    };

    public int getTitle() {
        return title;
    }

    public int getTextNote() {
        return textNote;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(title);
        dest.writeInt(textNote);
    }
}
