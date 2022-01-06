package com.gmail.pavlovsv93.notepad.notes;

import com.gmail.pavlovsv93.notepad.ListNotesInterface;

import java.util.List;

public class NotesPresenter {

    private ListNotesInterface view;

    private NodesRepository repository;


    public NotesPresenter(ListNotesInterface view, NodesRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void refresh(){
        List<Notes> result = repository.getAllNotes();
        view.showNotes(result);
    }
}
