package com.example.noteexo4.data.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.noteexo4.data.db.dao.NoteDao
import com.example.noteexo4.data.db.database.NoteDB
import com.example.noteexo4.data.db.entity.Note


class NoteRepository(application: Application) {
    private  var noteDao: NoteDao
    private  var allNote: LiveData<List<Note>>


    init {
        val database: NoteDB = NoteDB.getInstance(
            application
        )!!
        noteDao=database.NoteDAO()
        allNote=noteDao.getAllNote()
    }




    fun ajouter(note : Note) {
        ajouterNoteAsyncTask(
            noteDao
        ).execute(note)
    }

    fun modifier(note : Note){

        modifierNoteAsyncTask(
            noteDao
        ).execute(note)
    }

    fun supprimer(note: Note){

        supprimerNoteAsyncTask(
            noteDao
        ).execute(note)
    }



    fun getAllNote(): LiveData<List<Note>> {
        return allNote
    }



    companion object{

        private  class ajouterNoteAsyncTask(noteDao: NoteDao) :
            AsyncTask<Note, Void, Void>() {
            private  var noteDao: NoteDao = noteDao


            override fun doInBackground(vararg params: Note?): Void? {

                params[0]?.let { noteDao.ajouter(it) }
                return null
            }

        }

        private  class modifierNoteAsyncTask(noteDao: NoteDao) :
            AsyncTask<Note, Void, Void>() {
            private  var noteDao: NoteDao = noteDao


            override fun doInBackground(vararg params: Note?): Void? {

                params[0]?.let { noteDao.modifier(it) }
                return null
            }

        }

        private  class supprimerNoteAsyncTask(noteDao: NoteDao) :
            AsyncTask<Note, Void, Void>() {
            private  var noteDao: NoteDao = noteDao


            override fun doInBackground(vararg params: Note?): Void? {

                params[0]?.let { noteDao.supprimer(it) }
                return null
            }

        }




    }

}