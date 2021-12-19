package com.gmail.pavlovsv93.notepadv2.domain.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.gmail.pavlovsv93.notepadv2.domain.Note;

import java.util.List;

@Dao
public interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) // Будет изменять текущий элемент БД, если id одинаковый
    void insert(Note note);

    @Delete
    void delete(Note note);

    @Update
    void update(Note note);

    @Query("SELECT * FROM Note WHERE done = :res_done ")
    List<Note> listNoteCheck(boolean res_done);

//    @Query("SELECT * FROM Note WHERE done = :res_done")
//    List<Note> listNoteNoCheck(boolean res_done);
}
