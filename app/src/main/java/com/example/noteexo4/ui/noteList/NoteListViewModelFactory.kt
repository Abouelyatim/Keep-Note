package com.example.noteexo4.ui.noteList

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class NoteListViewModelFactory(
    private val application: Application
)
    : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NoteListViewModel(
            application
        ) as T
    }
}