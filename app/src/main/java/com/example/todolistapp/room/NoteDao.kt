package com.example.todolistapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface  NoteDao {
    @Insert
    fun addNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Query("SELECT * FROM note_tb ORDER BY id ASC")
    fun readAllNote(): LiveData<List<Note>>

    @Query("DELETE FROM note_tb")
    fun deleteAllNotes()


//    @Delete
//    suspend fun deleteNote(note: Note)
}