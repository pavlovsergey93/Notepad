package com.gmail.pavlovsv93.notepad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentResultListener;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.DatePicker;

import com.gmail.pavlovsv93.notepad.detail.NotesDetailActivity;
import com.gmail.pavlovsv93.notepad.detail.NotesDetailsFragment;
import com.gmail.pavlovsv93.notepad.notes.Notes;

public class NotepadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);

        DatePicker dp;
        getSupportFragmentManager()
                .setFragmentResultListener(ListNotesFragment.RESULT_KEY, this, new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        Notes notes = result.getParcelable(ListNotesFragment.ARG_NODE);

                        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.fragment_container_two, NotesDetailsFragment.newInstance(notes))
                                    .commit();

                        } else {
                            Intent intent = new Intent(NotepadActivity.this, NotesDetailActivity.class);
                            intent.putExtra(NotesDetailActivity.EXTRA_NOTE, notes);
                            startActivity(intent);
                        }
                    }
                });

        dp = findViewById(R.id.data_picker);
    }
}