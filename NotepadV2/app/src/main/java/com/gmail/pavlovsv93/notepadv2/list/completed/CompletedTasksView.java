package com.gmail.pavlovsv93.notepadv2.list.completed;

import com.gmail.pavlovsv93.notepadv2.domain.Note;

import java.util.List;

public interface CompletedTasksView {

    void showCompletedTasks(List<Note> notes);

    void showProgress();

    void hideProgress();

}
