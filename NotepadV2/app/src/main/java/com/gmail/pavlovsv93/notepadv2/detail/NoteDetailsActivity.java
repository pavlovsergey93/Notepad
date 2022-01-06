package com.gmail.pavlovsv93.notepadv2.detail;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.gmail.pavlovsv93.notepadv2.R;
import com.gmail.pavlovsv93.notepadv2.domain.Note;

public class NoteDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_DETAILS = "EXTRA_DETAILS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);


        FragmentManager fragmentManager = getSupportFragmentManager();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
        } else {
            if (savedInstanceState == null){
                Note note = getIntent().getParcelableExtra(EXTRA_DETAILS);
                fragmentManager.beginTransaction()
                        .replace(R.id.container_details, NoteDetailsFragment.newInstance(note))
                        .commit();
            }
        }
    }
}