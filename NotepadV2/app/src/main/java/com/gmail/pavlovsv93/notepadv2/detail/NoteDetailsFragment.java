package com.gmail.pavlovsv93.notepadv2.detail;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gmail.pavlovsv93.notepadv2.R;
import com.gmail.pavlovsv93.notepadv2.domain.Note;

public class NoteDetailsFragment extends Fragment {

    public static final String ARG_DETAILS_NOTE = "ARG_DETAILS_NOTE";

    public static NoteDetailsFragment newInstance(Note note) {
        NoteDetailsFragment ndf = new NoteDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_DETAILS_NOTE, note);
        ndf.setArguments(args);
        return ndf;
    }

    public NoteDetailsFragment(){
        super(R.layout.fragment_note_details);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Note note = requireArguments().getParcelable(ARG_DETAILS_NOTE);

        TextView textTitleDetails = view.findViewById(R.id.text_title_details);
        textTitleDetails.setText(note.getTitle());

        TextView textDetails = view.findViewById(R.id.text_details);
        textDetails.setText(note.getText());
    }
}
