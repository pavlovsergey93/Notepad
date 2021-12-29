package com.gmail.pavlovsv93.notepadv2.list.current;

import com.gmail.pavlovsv93.notepadv2.domain.Note;

import java.util.List;

public interface CurrentTasksView {

    void showCurrentTasks(List<Note> notes);

    void showProgress();

    void hideProgress();

}
