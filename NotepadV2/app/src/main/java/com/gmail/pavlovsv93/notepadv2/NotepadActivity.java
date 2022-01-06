package com.gmail.pavlovsv93.notepadv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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
}