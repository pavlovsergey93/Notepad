package com.gmail.pavlovsv93.notepadv2.addNote;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.gmail.pavlovsv93.notepadv2.R;

public class AddNoteActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.add_note_container, new AddNoteFragment())
                .commit();

        final EditText titleText;
        final EditText noteText;
        titleText = findViewById(R.id.edit_text_title);
        noteText = findViewById(R.id.edit_text);

        //Сохранение заметки
        findViewById(R.id.menu_tb_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //код для сохранения
                Toast.makeText(AddNoteActivity.this, "Сохранено", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        //Отмена добавления заметки
        findViewById(R.id.menu_tb_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
