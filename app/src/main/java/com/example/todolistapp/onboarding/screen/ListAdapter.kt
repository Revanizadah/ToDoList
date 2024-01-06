package com.example.todolistapp.onboarding.screen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import com.example.todolistapp.room.Note
import com.example.todolistapp.room.NoteViewModel
import kotlinx.android.synthetic.main.list_task.view.*

class ListAdapter(private val context: Context, private val mNoteViewModel: NoteViewModel) :
    RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var noteList = emptyList<Note>()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_task,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentNote = noteList[position]
        holder.itemView.nama_task.text = currentNote.judul
        holder.itemView.deskripsi_task.text = currentNote.deskripsi
        holder.itemView.date_task.text = currentNote.date

        holder.itemView.rowList.setOnClickListener {
            val action = ListTaskDirections.actionListTaskToUpdateFragment(currentNote)
            holder.itemView.findNavController().navigate(action)
        }

        holder.itemView.findViewById<Button>(R.id.btnDelete).setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Delete Note")
                .setMessage("Are you sure you want to delete this note?")
                .setPositiveButton("Yes") { _, _ ->
                    mNoteViewModel.deleteNote(currentNote)
                }
                .setNegativeButton("No", null)
                .show()
        }
    }

    fun setData(note: List<Note>) {
        this.noteList = note
        notifyDataSetChanged()
    }
}
