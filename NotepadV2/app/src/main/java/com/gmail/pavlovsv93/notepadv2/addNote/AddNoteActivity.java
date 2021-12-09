package com.gmail.pavlovsv93.notepadv2.addNote;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.gmail.pavlovsv93.notepadv2.R;
import com.gmail.pavlovsv93.notepadv2.detail.NoteDetailsFragment;
import com.gmail.pavlovsv93.notepadv2.domain.Note;

public class AddNoteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.add_note_container, new AddNoteFragment())
                .commit();
    }

}
