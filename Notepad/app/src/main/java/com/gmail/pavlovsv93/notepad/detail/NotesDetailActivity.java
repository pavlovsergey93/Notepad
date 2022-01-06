package com.gmail.pavlovsv93.notepad.detail;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.gmail.pavlovsv93.notepad.R;
import com.gmail.pavlovsv93.notepad.notes.Notes;

public class NotesDetailActivity extends AppCompatActivity {

    public static final String EXTRA_NOTE = "EXTRA_NOTE";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detiles_notes);

        Notes note = getIntent().getParcelableExtra(EXTRA_NOTE);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.container_two, NotesDetailsFragment.newInstance(note))
                .commit();
    }
}
