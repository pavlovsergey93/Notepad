package com.gmail.pavlovsv93.notepadv2.addNote;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.gmail.pavlovsv93.notepadv2.App;
import com.gmail.pavlovsv93.notepadv2.R;
import com.gmail.pavlovsv93.notepadv2.domain.Note;
import com.google.android.material.snackbar.Snackbar;

import java.util.Date;
import java.util.Locale;

public class AddNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.add_note_container, new AddNoteFragment())
                .commit();

        //Сохранение заметки
        findViewById(R.id.menu_tb_save).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                //код для сохранения
                EditText titleText = findViewById(R.id.edit_text_title);
                EditText noteText = findViewById(R.id.edit_text);
                if (titleText.getText().length() == 0) {
                    Snackbar.make(v, R.string.save_add_exp, Snackbar.LENGTH_SHORT)
                            .show();
                } else if (titleText.getText().length() != 0) {
                    Note note = new Note();
                    note.title = titleText.getText().toString();
                    note.text = noteText.getText().toString();
                    note.done = false;
                    note.timestemp = timeNow();
                    App.getInstance().getNotesDao().insert(note);
                    Toast.makeText(AddNoteActivity.this, "Сохранено", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });

        //Отмена добавления заметки
        findViewById(R.id.menu_tb_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.edit_text);
                EditText editTextTitle = findViewById(R.id.edit_text_title);

                if (editTextTitle.getText().length() != 0 || editText.getText().length() != 0) {
                    Snackbar.make(v, R.string.close_add_bar, Snackbar.LENGTH_INDEFINITE)
                            .setAction(R.string.close_btn_text_bar, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    finish();
                                }
                            })
                            .show();
                } else {
                    finish();
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String timeNow() {
        // Текущее время
        Date currentDate = new Date();
        // Форматирование времени как "день.месяц.год"
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
        // Форматирование времени как "часы:минуты:секунды"
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String timeText = timeFormat.format(currentDate);
        String result = dateText + " " + timeText;
        return result;
    }
}
