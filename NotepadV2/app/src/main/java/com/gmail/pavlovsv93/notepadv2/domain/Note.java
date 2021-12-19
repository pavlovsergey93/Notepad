package com.gmail.pavlovsv93.notepadv2.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Note implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "text")
    public String text;

    @ColumnInfo(name = "time")
    public String timestemp;

    @ColumnInfo(name = "done")
    public boolean done;

    public Note() {
    }

    protected Note(Parcel in) {
        id = in.readInt();
        title = in.readString();
        text = in.readString();
        timestemp = in.readString();
        done = in.readByte() != 0;
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

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(text);
        dest.writeString(timestemp);
        dest.writeByte((byte) (done ? 1 : 0));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return id == note.id && done == note.done && Objects.equals(title, note.title) && Objects.equals(text, note.text) && Objects.equals(timestemp, note.timestemp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, text, timestemp, done);
    }
}
