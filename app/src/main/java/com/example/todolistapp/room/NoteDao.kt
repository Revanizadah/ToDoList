package com.example.todolistapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface  NoteDao {
    @Insert
    fun addNote(note: Note)

    @Query("SELECT * FROM note_tb ORDER BY id ASC")
    fun readAllNote(): LiveData<List<Note>>

//    @Update
//    suspend fun updateNote(note: Note)
//
//    @Delete
//    suspend fun deleteNote(note: Note)
}