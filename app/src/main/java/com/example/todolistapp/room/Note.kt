package com.example.todolistapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "note_tb")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val judul: String,
    val deskripsi: String
)



