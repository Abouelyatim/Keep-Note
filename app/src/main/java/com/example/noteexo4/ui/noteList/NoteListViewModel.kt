package com.example.noteexo4.ui.noteList

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.noteexo4.data.db.entity.Note
import com.example.noteexo4.data.repository.NoteRepository

class NoteListViewModel (@androidx.annotation.NonNull application: Application?) : ViewModel() {
    private  var repository: NoteRepository
    private  var allNote: LiveData<List<Note>>

    init {
        repository=
            NoteRepository(
                application!!
            )
        allNote=repository.getAllNote()

    }


    fun getAllNote(): LiveData<List<Note>> {
        return allNote
    }

    fun supprimer(note: Note){

        repository.supprimer(note)
    }


}