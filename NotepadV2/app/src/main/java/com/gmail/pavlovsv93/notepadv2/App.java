package com.gmail.pavlovsv93.notepadv2;

import android.app.Application;

import androidx.room.Room;

import com.gmail.pavlovsv93.notepadv2.domain.database.DBNotes;
import com.gmail.pavlovsv93.notepadv2.domain.database.NotesDao;

public class App extends Application {

    private DBNotes dbNotes;
    private NotesDao notesDao;

    private static App instance;

    public static App getInstance(){
        return instance;
    }

    public void setDbNotes(DBNotes dbNotes) {
        this.dbNotes = dbNotes;
    }

    public void setNotesDao(NotesDao notesDao) {
        this.notesDao = notesDao;
    }

    public DBNotes getDbNotes() {
        return dbNotes;
    }

    public NotesDao getNotesDao() {
        return notesDao;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        dbNotes = Room.databaseBuilder(getApplicationContext(), DBNotes.class,"NoteDB")
                .allowMainThreadQueries()
                .build();
        notesDao = dbNotes.notesDao();
    }
}
