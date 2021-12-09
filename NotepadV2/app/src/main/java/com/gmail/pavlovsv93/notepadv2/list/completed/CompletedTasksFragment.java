package com.gmail.pavlovsv93.notepadv2.list.completed;

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

import com.gmail.pavlovsv93.notepadv2.R;
import com.gmail.pavlovsv93.notepadv2.domain.InMemoryNotesRepository;
import com.gmail.pavlovsv93.notepadv2.domain.Note;
import com.gmail.pavlovsv93.notepadv2.list.NotesPresenter;

import java.util.List;

public class CompletedTasksFragment extends Fragment implements CompletedTasksView {

    private LinearLayout completedContainer;

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

        for (Note note : notes){
            View itemView = LayoutInflater.from(requireContext()).inflate(R.layout.item_completed_task, completedContainer, false);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Toast.makeText(requireContext(), note.getTitle(), Toast.LENGTH_SHORT).show();
                }
            });
            TextView textTitle = itemView.findViewById(R.id.text_title_comp);
            textTitle.setText(note.getTitle());

            Button btnResuming = itemView.findViewById(R.id.btn_resuming);
            btnResuming.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(requireContext(), "Востановить", Toast.LENGTH_SHORT).show();
                }
            });

            Button btnDelete = itemView.findViewById(R.id.btn_delete);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(requireContext(), "Запись удалена", Toast.LENGTH_SHORT).show();
                }
            });
            completedContainer.addView(itemView);
        }

    }
}
