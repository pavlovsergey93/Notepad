package com.gmail.pavlovsv93.notepadv2.list;

import com.gmail.pavlovsv93.notepadv2.domain.Callback;
import com.gmail.pavlovsv93.notepadv2.domain.Note;
import com.gmail.pavlovsv93.notepadv2.domain.NotesRepository;
import com.gmail.pavlovsv93.notepadv2.list.completed.CompletedTasksView;
import com.gmail.pavlovsv93.notepadv2.list.current.CurrentTasksView;

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

        viewCurrent.showProgress();

        repository.getAllNotesCurr(new Callback<List<Note>>() {
            @Override
            public void onSucces(List<Note> result) {
                viewCurrent.showCurrentTasks(result);
                viewCurrent.hideProgress();
            }

            @Override
            public void onError(Throwable error) {
                viewCurrent.hideProgress();

            }
        });


    }

    public NotesPresenter(CompletedTasksView viewCompleted, NotesRepository repository) {
        this.viewCompleted = viewCompleted;
        this.repository = repository;
    }

    public void getListCompleted(){

        viewCompleted.showProgress();

        repository.getAllNotesComp(new Callback<List<Note>>() {
            @Override
            public void onSucces(List<Note> result) {
                viewCompleted.showCompletedTasks(result);
                viewCompleted.hideProgress();
            }

            @Override
            public void onError(Throwable error) {
                viewCompleted.hideProgress();
            }
        });

    }
}
