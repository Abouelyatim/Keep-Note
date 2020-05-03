package com.example.noteexo4.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.noteexo4.data.db.entity.Note

@Dao
interface  NoteDao {

    @Insert
    fun ajouter(note : Note)

    @Update
    fun modifier(note : Note)

    @Delete
    fun supprimer(note: Note)


    @Query("SELECT * FROM note_table")
    fun getAllNote(): LiveData<List<Note>>
}