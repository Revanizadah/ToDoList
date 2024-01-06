package com.example.todolistapp.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.sql.Date

@Parcelize
@Entity(tableName = "note_tb")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val judul: String,
    val deskripsi: String,
    val date: String
): Parcelable



