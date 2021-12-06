package com.gmail.pavlovsv93.notepad.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gmail.pavlovsv93.notepad.R;
import com.gmail.pavlovsv93.notepad.notes.Notes;

public class NotesDetailsFragment extends Fragment {

    private static final String KEY_NOTES = "KEY_NOTES";

    public static NotesDetailsFragment newInstance(Notes note){
        NotesDetailsFragment ndf = new NotesDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(KEY_NOTES, note);
        ndf.setArguments(args);
        return ndf;
    }

    public NotesDetailsFragment(){
        super(R.layout.item_notes);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Notes note = requireArguments().getParcelable(KEY_NOTES);

        EditText textTitle = view.findViewById(R.id.title_edit_text);
        textTitle.setText(note.getTitle());

        EditText textNode = view.findViewById(R.id.note_text);
        textNode.setText(note.getTextNote());

    }
}
