package com.example.noteexo4.ui.noteAddEdit

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class NoteAddEditViewModelFactory(
    private val application: Application
)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NoteAddEditViewModel(application) as T
    }
}