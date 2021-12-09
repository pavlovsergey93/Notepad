package com.gmail.pavlovsv93.notepadv2.list.current;

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

public class CurrentTasksFragment extends Fragment implements CurrentTasksView {

    private LinearLayout currentContainer;

    private NotesPresenter presenterCurrent;

    public static final String ARG_NOTE_LIST_CURRENT = "ARG_NOTE_LIST_CURRENT";

    public CurrentTasksFragment(){
        super(R.layout.fragment_current_tasks);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenterCurrent = new NotesPresenter(this, new InMemoryNotesRepository());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        currentContainer = view.findViewById(R.id.current_container);

        presenterCurrent.getListCurrent();

    }

    @Override
    public void showCurrentTasks(List<Note> notes) {
        // для каждого элемента списка -->
        for (Note note : notes) {
            // найти его View-шку
            View itemView = LayoutInflater.from(requireContext()).inflate(R.layout.item_current_task, currentContainer, false);
            // сделать ее кликабельной
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(requireContext(), note.getTitle(), Toast.LENGTH_SHORT).show();
                }
            });
            // найти все view элементы на найденой View-ше
            TextView textTitle = itemView.findViewById(R.id.text_title);
            textTitle.setText(note.getTitle());

            TextView textDate = itemView.findViewById(R.id.text_date);
            textDate.setText(note.getText());
            // кнопка редактирования текста
            Button btnEdit = itemView.findViewById(R.id.btn_edit);
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(requireContext(), "Редактировать заметку", Toast.LENGTH_SHORT).show();
                }
            });
            // кнопка перемещения задачи из текущего списка в выполненный
            Button btnDone = itemView.findViewById(R.id.btn_done);
            btnDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(requireContext(), "Заметка выполнена", Toast.LENGTH_SHORT).show();
                }
            });
            currentContainer.addView(itemView);
        }
    }
}
