package com.gmail.pavlovsv93.notepadv2.domain;

import java.util.List;

public interface NotesRepository {

    List<Note> getAllNotesCurr();

    List<Note> getAllNotesComp();

}
