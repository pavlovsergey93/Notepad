package com.gmail.pavlovsv93.notepadv2.list;

import com.gmail.pavlovsv93.notepadv2.domain.Note;
import com.gmail.pavlovsv93.notepadv2.domain.NotesRepository;
import com.gmail.pavlovsv93.notepadv2.list.completed.CompletedTasksView;
import com.gmail.pavlovsv93.notepadv2.list.current.CurrentTasksView;

import java.util.ArrayList;
import java.util.List;

public class NotesPresenter {
    CurrentTasksView viewCurrent;
    CompletedTasksView viewCompleted;
    NotesRepository repository;


    public NotesPresenter(CurrentTasksView viewCurrent, NotesRepository repository) {
        this.viewCurrent = viewCurrent;
        this.repository = repository;
    }

    public void getListCurrent() {
        List<Note> noteList = new ArrayList<>();
        noteList = repository.getAllNotesCurr();
        viewCurrent.showCurrentTasks(noteList);

    }

    public NotesPresenter(CompletedTasksView viewCompleted, NotesRepository repository) {
        this.viewCompleted = viewCompleted;
        this.repository = repository;
    }

    public void getListCompleted(){
        List<Note> noteList = new ArrayList<>();
        noteList = repository.getAllNotesComp();
        viewCompleted.showCompletedTasks(noteList);
    }
}
