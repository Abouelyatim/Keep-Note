package com.example.noteexo4.data.db.database

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.noteexo4.data.db.dao.NoteDao
import com.example.noteexo4.data.db.entity.Note


@Database(entities = [Note::class], version = 1)
abstract class NoteDB: RoomDatabase() {

    abstract fun NoteDAO(): NoteDao

    companion object {
        private var instance: NoteDB? = null


        fun getInstance(context: Context): NoteDB? {
            if (instance == null) {

                instance = Room.databaseBuilder(context.applicationContext,
                    NoteDB::class.java, "note_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()

            }
            return instance
        }

        private val roomCallback: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                instance?.let { PopulateDbAsyncTask(
                    it
                ).execute()

                }
            }
        }

        private open class PopulateDbAsyncTask(db: NoteDB) :
            AsyncTask<Void?, Void?, Void?>() {
            private  var noteDao: NoteDao = db.NoteDAO()


            override fun doInBackground(vararg params: Void?): Void? {



                noteDao.ajouter(Note("20-11-13","titre","kdlkldklddklkld dsnjdfjksjdk sdnfkl","FF77FF"))

                noteDao.ajouter(Note("20-11-14","titre1","kdlkldklddklkld dsnjdfjksjdk sdnfkl1","FF77FF"))

                return null
            }

        }
    }

}