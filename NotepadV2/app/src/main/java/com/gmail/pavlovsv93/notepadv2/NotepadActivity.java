package com.gmail.pavlovsv93.notepadv2;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import com.gmail.pavlovsv93.notepadv2.addNote.AddNoteActivity;
import com.gmail.pavlovsv93.notepadv2.detail.NoteDetailsActivity;
import com.gmail.pavlovsv93.notepadv2.detail.NoteDetailsFragment;
import com.gmail.pavlovsv93.notepadv2.domain.Note;
import com.gmail.pavlovsv93.notepadv2.list.completed.CompletedTasksFragment;
import com.gmail.pavlovsv93.notepadv2.list.current.CurrentTasksFragment;
import com.gmail.pavlovsv93.notepadv2.navdrawer.InfoFragment;
import com.gmail.pavlovsv93.notepadv2.navdrawer.SettingFragment;
import com.google.android.material.navigation.NavigationView;

public class NotepadActivity extends AppCompatActivity {

    private Note selectNote;
    public static final String ARG_NOTE_MAIN = "ARG_NOTE_MAIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);

        if (savedInstanceState != null && savedInstanceState.containsKey(ARG_NOTE_MAIN)) {
            selectNote = savedInstanceState.getParcelable(ARG_NOTE_MAIN);

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                showDetails();
            }
        }
        // создаем фрагметн менеджер
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Выводим список задач
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, new CurrentTasksFragment())
                .commit();

        // Переходим на новую Activity для создания заметки
        findViewById(R.id.menu_add_note).setOnClickListener(new View.OnClickListener() {
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
                        .replace(R.id.fragment_container, new CompletedTasksFragment())
                        .commit();
            }
        });

        fragmentManager.setFragmentResultListener(CurrentTasksFragment.KEY_NOTE_LIST_CURRENT, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                selectNote = result.getParcelable(CurrentTasksFragment.ARG_NOTE_LIST_CURRENT);

                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

                    showDetails();

                } else {

                    Intent intent = new Intent(NotepadActivity.this, NoteDetailsActivity.class);
                    intent.putExtra(NoteDetailsActivity.EXTRA_DETAILS, selectNote);
                    startActivity(intent);
                }
            }
        });


        DrawerLayout drawer = findViewById(R.id.drawer_layuot);
        Toolbar toolbar = findViewById(R.id.toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.appbar_scrolling_view_behavior,
                R.string.bottom_sheet_behavior);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView nv = findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_nd_setting:
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, new SettingFragment(), "SettingFragment")
                                .commit();
                        drawer.closeDrawer(GravityCompat.START);
                        return true;

                    case R.id.menu_nd_info:
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, new InfoFragment(), "InfoFragment")
                                .commit();
                        drawer.closeDrawer(GravityCompat.START);
                        return true;
                }
                return false;
            }
        });
    }

    private void showDetails() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_two, NoteDetailsFragment.newInstance(selectNote))
                .commit();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (selectNote != null) {
            outState.putParcelable(ARG_NOTE_MAIN, selectNote);
        }
    }
}