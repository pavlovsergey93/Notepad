package com.gmail.pavlovsv93.notepadv2.domain.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.gmail.pavlovsv93.notepadv2.domain.Note;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class DBNotes extends RoomDatabase {
    public abstract NotesDao notesDao();
}
