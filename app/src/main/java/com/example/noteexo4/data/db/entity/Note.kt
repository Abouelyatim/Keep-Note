package com.example.noteexo4.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
class Note(

    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "titre") var titre:String,
    @ColumnInfo(name = "text") var text:String,
    @ColumnInfo(name = "couleur") var couleur:String ) {

    @PrimaryKey(autoGenerate = true) var  numero = 0

    fun setId(id:Int){
        numero=id
    }

}