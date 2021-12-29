package com.gmail.pavlovsv93.notepadv2.list.current;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.pavlovsv93.notepadv2.App;
import com.gmail.pavlovsv93.notepadv2.NotesAdapter;
import com.gmail.pavlovsv93.notepadv2.R;
import com.gmail.pavlovsv93.notepadv2.domain.InMemoryNotesRepository;
import com.gmail.pavlovsv93.notepadv2.domain.Note;
import com.gmail.pavlovsv93.notepadv2.list.NotesPresenter;

import java.util.List;

public class CurrentTasksFragment extends Fragment implements CurrentTasksView {

    private RecyclerView currentContainer;

    private NotesPresenter presenterCurrent;

    private NotesAdapter adapter;

    public static final String ARG_NOTE_LIST_CURRENT = "ARG_NOTE_LIST_CURRENT";
    public static final String KEY_NOTE_LIST_CURRENT = "KEY_NOTE_LIST_CURRENT";

    public CurrentTasksFragment() {
        super(R.layout.fragment_current_tasks);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenterCurrent = new NotesPresenter(this, new InMemoryNotesRepository());

        adapter = new NotesAdapter();
        adapter.setOnClick(new NotesAdapter.OnClick() {
            @Override
            public void onClick(Note note) {
                Bundle data = new Bundle();
                data.putParcelable(ARG_NOTE_LIST_CURRENT, note);
                getParentFragmentManager()
                        .setFragmentResult(KEY_NOTE_LIST_CURRENT, data);
            }

            @Override
            public void onClickEdit(Note note) {

            }

            @Override
            public void onClickDone(Note note) {
                note.done = true;
                App.getInstance().getNotesDao().update(note);
                updateView();
                Toast.makeText(requireContext(), "Заметка выполнена", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        currentContainer = view.findViewById(R.id.current_container);

        presenterCurrent.getListCurrent();

        currentContainer.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        currentContainer.setAdapter(adapter);
    }

    @Override
    public void showCurrentTasks(List<Note> notes) {

        adapter.setNoteList(notes);

        adapter.notifyDataSetChanged();

//        // для каждого элемента списка -->
//        for (Note note : notes) {
//            // найти его View-шку
//            View itemView = LayoutInflater.from(requireContext()).inflate(R.layout.item_current_task, currentContainer, false);
//            // сделать ее кликабельной
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Bundle data = new Bundle();
//                    data.putParcelable(ARG_NOTE_LIST_CURRENT, note);
//
//                    getParentFragmentManager()
//                            .setFragmentResult(KEY_NOTE_LIST_CURRENT, data);
//                    //                   Toast.makeText(requireContext(), note.getTitle(), Toast.LENGTH_SHORT).show();
//                }
//            });
//            // найти все view элементы на найденой View-ше
//            TextView textTitle = itemView.findViewById(R.id.text_title);
//            textTitle.setText(note.title);
//
//            TextView textDate = itemView.findViewById(R.id.text_date);
//            textDate.setText(note.timestemp);
//            // кнопка редактирования текста
//            Button btnEdit = itemView.findViewById(R.id.btn_edit);
//            btnEdit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                }
//            });
//            // кнопка перемещения задачи из текущего списка в выполненный
//            Button btnDone = itemView.findViewById(R.id.btn_done);
//            btnDone.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    note.done = true;
//                    App.getInstance().getNotesDao().update(note);
//                    updateView();
//                    Toast.makeText(requireContext(), "Заметка выполнена", Toast.LENGTH_SHORT).show();
//                }
//            });
//            currentContainer.addView(itemView);
//        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    private void updateView() {
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new CurrentTasksFragment())
                .commit();
    }
}
