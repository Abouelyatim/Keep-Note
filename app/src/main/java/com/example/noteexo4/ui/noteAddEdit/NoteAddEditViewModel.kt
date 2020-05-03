package com.example.noteexo4.ui.noteAddEdit

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.noteexo4.data.db.entity.Note
import com.example.noteexo4.data.repository.NoteRepository


class NoteAddEditViewModel (@androidx.annotation.NonNull application: Application?): ViewModel() {
    private  var repository: NoteRepository

    init {
        repository=
            NoteRepository(
                application!!
            )


    }

    fun ajouter(note : Note){
        repository.ajouter(note)
    }

    fun modifier(note : Note){

        repository.modifier(note)
    }
}