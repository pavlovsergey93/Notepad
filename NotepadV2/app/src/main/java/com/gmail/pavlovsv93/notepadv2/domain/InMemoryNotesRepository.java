package com.gmail.pavlovsv93.notepadv2.domain;

import android.os.Handler;
import android.os.Looper;

import com.gmail.pavlovsv93.notepadv2.App;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class InMemoryNotesRepository implements NotesRepository {

    public static final NotesRepository INSTANCE = new InMemoryNotesRepository();

    private Executor executor = Executors.newSingleThreadExecutor();

    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void getAllNotesComp(Callback<List<Note>> callback) {

        executor.execute(new Runnable() {
            @Override
            public void run() {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSucces(App.getInstance().getNotesDao().listNoteCheck(true));
                    }
                });
            }
        });

    }

    @Override
    public void getAllNotesCurr(Callback<List<Note>> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSucces(App.getInstance().getNotesDao().listNoteCheck(false));
                    }
                });
            }
        });

    }
}
