package com.example.todolistapp.room

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {

    val readAllNote: LiveData<List<Note>> = noteDao.readAllNote()

    suspend fun addNote(note: Note){
        noteDao.addNote(note)
    }
}