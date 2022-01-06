package com.gmail.pavlovsv93.notepadv2.domain;

import com.gmail.pavlovsv93.notepadv2.App;

import java.util.ArrayList;
import java.util.List;

public class InMemoryNotesRepository implements NotesRepository {
    @Override
    public List<Note> getAllNotesCurr() {
        List<Note> result = new ArrayList<>();
        result = App.getInstance().getNotesDao().listNoteCheck(false);
        return result;
    }

    @Override
    public List<Note> getAllNotesComp() {
        List<Note> result = new ArrayList<>();
        result = App.getInstance().getNotesDao().listNoteCheck(true);
        return result;
    }
}
