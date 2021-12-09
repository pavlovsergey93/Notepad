package com.gmail.pavlovsv93.notepadv2.domain;

import com.gmail.pavlovsv93.notepadv2.R;

import java.util.ArrayList;
import java.util.List;

public class InMemoryNotesRepository implements NotesRepository {
    @Override
    public List<Note> getAllNotesCurr() {
        ArrayList<Note> result = new ArrayList<>();
        result.add(new Note(R.string.note_1, R.string.data_1));
        result.add(new Note(R.string.note_2, R.string.data_2));
        result.add(new Note(R.string.note_3, R.string.data_3));
        result.add(new Note(R.string.note_4, R.string.data_4));
        result.add(new Note(R.string.note_5, R.string.data_5));

        return result;
    }

    @Override
    public List<Note> getAllNotesComp() {
        ArrayList<Note> result = new ArrayList<>();
        result.add(new Note(R.string.note_6, R.string.note_6));
        result.add(new Note(R.string.note_7, R.string.note_7));
        result.add(new Note(R.string.note_8, R.string.note_8));
        result.add(new Note(R.string.note_9, R.string.note_9));
        result.add(new Note(R.string.note_10, R.string.note_10));
        return result;
    }
}
