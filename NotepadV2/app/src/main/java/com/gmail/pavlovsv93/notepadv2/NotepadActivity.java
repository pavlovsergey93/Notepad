package com.gmail.pavlovsv93.notepadv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Toast;

import com.gmail.pavlovsv93.notepadv2.addNote.AddNoteActivity;
import com.gmail.pavlovsv93.notepadv2.detail.NoteDetailsActivity;
import com.gmail.pavlovsv93.notepadv2.detail.NoteDetailsFragment;
import com.gmail.pavlovsv93.notepadv2.domain.Note;
import com.gmail.pavlovsv93.notepadv2.list.completed.CompletedTasksFragment;
import com.gmail.pavlovsv93.notepadv2.list.current.CurrentTasksFragment;

public class NotepadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);

        // создаем фрагметн менеджер
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Выводим список задач
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, new CurrentTasksFragment())
                .commit();

        // Переходим на новую Activity для создания заметки
        findViewById(R.id.floating_btm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotepadActivity.this, AddNoteActivity.class);
                startActivity(intent);
            }
        });

        // отобразить список задач
        findViewById(R.id.menu_current_tasks).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, new CurrentTasksFragment())
                        .commit();
            }
        });

        //отобразить список выполненых задач
        findViewById(R.id.menu_completed_tasks).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container,new CompletedTasksFragment())
                        .commit();
            }
        });
    }
}