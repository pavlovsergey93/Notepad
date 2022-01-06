package com.gmail.pavlovsv93.notepadv2.domain;

import java.util.List;

public interface NotesRepository {

    void getAllNotesComp(Callback<List<Note>> callback);

    void getAllNotesCurr(Callback<List<Note>> callback);
}
