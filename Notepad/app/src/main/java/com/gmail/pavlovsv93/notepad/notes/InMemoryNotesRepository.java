package com.gmail.pavlovsv93.notepad.notes;

import com.gmail.pavlovsv93.notepad.R;

import java.util.ArrayList;
import java.util.List;

public class InMemoryNotesRepository implements NodesRepository{
    @Override
    public List<Notes> getAllNotes() {
        ArrayList<Notes> notepad = new ArrayList<>();
        notepad.add(new Notes(R.string.note_1, R.drawable.ic_baseline_note_24));
        notepad.add(new Notes(R.string.note_2, R.drawable.ic_baseline_note_24));
        notepad.add(new Notes(R.string.note_3, R.drawable.ic_baseline_note_24));
        notepad.add(new Notes(R.string.note_4, R.drawable.ic_baseline_note_24));
        notepad.add(new Notes(R.string.note_5, R.drawable.ic_baseline_note_24));
        return notepad;
    }
}
