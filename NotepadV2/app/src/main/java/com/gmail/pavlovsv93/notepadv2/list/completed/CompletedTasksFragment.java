package com.gmail.pavlovsv93.notepadv2.list.completed;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.gmail.pavlovsv93.notepadv2.App;
import com.gmail.pavlovsv93.notepadv2.R;
import com.gmail.pavlovsv93.notepadv2.dialogs.DeleteDialogFragment;
import com.gmail.pavlovsv93.notepadv2.domain.InMemoryNotesRepository;
import com.gmail.pavlovsv93.notepadv2.domain.Note;
import com.gmail.pavlovsv93.notepadv2.list.NotesPresenter;

import java.util.List;

public class CompletedTasksFragment extends Fragment implements CompletedTasksView {

    private LinearLayout completedContainer;

    public static final String ARG_NOTE_DELETE = "ARG_NOTE_DELETE";
    public static final String KEY_NOTE_DELETE = "KEY_NOTE_DELETE";

    private NotesPresenter presenter;

    public CompletedTasksFragment() {
        super(R.layout.fragment_completed_tasks);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new NotesPresenter(this, new InMemoryNotesRepository());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        completedContainer = view.findViewById(R.id.completed_container);

        presenter.getListCompleted();

    }

    @Override
    public void showCompletedTasks(List<Note> notes) {

        for (Note note : notes) {
            View itemView = LayoutInflater.from(requireContext()).inflate(R.layout.item_completed_task, completedContainer, false);

            TextView textTitle = itemView.findViewById(R.id.text_title_comp);
            textTitle.setText(note.title);

            Button btnResuming = itemView.findViewById(R.id.btn_resuming);
            btnResuming.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    note.done = false;
                    App.getInstance().getNotesDao().update(note);
                    updateView();
                    Toast.makeText(requireContext(), "Запись востоновлена", Toast.LENGTH_SHORT).show();
                }
            });

            Button btnDelete = itemView.findViewById(R.id.btn_delete);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DeleteDialogFragment.newInstans(note).show(getParentFragmentManager(), "DeleteDialogFragment");
                    getParentFragmentManager().setFragmentResultListener(DeleteDialogFragment.KEY_DIALOG, getViewLifecycleOwner(), new FragmentResultListener() {
                        @Override
                        public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                            switch (result.getInt(DeleteDialogFragment.ARG_DIALOG)) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    App.getInstance().getNotesDao().delete(note);
                                    updateView();
                                    break;
                            }
                        }
                    });
                }
            });


            completedContainer.addView(itemView);
        }

    }

    private void updateView() {
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new CompletedTasksFragment())
                .commit();
    }
}
