package com.gmail.pavlovsv93.notepad;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gmail.pavlovsv93.notepad.detail.NotesDetailActivity;
import com.gmail.pavlovsv93.notepad.detail.NotesDetailsFragment;
import com.gmail.pavlovsv93.notepad.notes.InMemoryNotesRepository;
import com.gmail.pavlovsv93.notepad.notes.Notes;
import com.gmail.pavlovsv93.notepad.notes.NotesPresenter;

import java.util.List;

public class ListNotesFragment extends Fragment implements ListNotesInterface {


    public static final String ARG_NODE = "ARG_NODE";
    public static final String RESULT_KEY = "RESULT_KEY";
    private LinearLayout nodesContainer;
    private NotesPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new NotesPresenter(this, new InMemoryNotesRepository());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nodesContainer = view.findViewById(R.id.notes_container);

        presenter.refresh();
    }

    @Override
    public void showNotes(List<Notes> notesList) {

        for (Notes notes : notesList) {

            View itemView = LayoutInflater.from(requireContext()).inflate(R.layout.item_notes_list, nodesContainer, false);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle data = new Bundle();
                    data.putParcelable(ARG_NODE, notes);

                    getParentFragmentManager()
                            .setFragmentResult(RESULT_KEY, data);

//                    Intent intent = new Intent(requireActivity(), NotesDetailActivity.class);
//                    intent.putExtra(NotesDetailActivity.EXTRA_NOTE, notes);
//                    startActivity(intent);

                    Toast.makeText(requireContext(), notes.getTitle(), Toast.LENGTH_SHORT).show();
                }
            });

            TextView titleTextView = itemView.findViewById(R.id.title_text_view);
            titleTextView.setText(notes.getTitle());
            nodesContainer.addView(itemView);
        }

    }
}
