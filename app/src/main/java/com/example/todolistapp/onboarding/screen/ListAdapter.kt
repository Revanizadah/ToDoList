package com.example.todolistapp.onboarding.screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import com.example.todolistapp.room.Note
import kotlinx.android.synthetic.main.fragment_list_task.view.*
import kotlinx.android.synthetic.main.list_task.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var noteList = emptyList<Note>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_task, parent, false))
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = noteList[position]
        holder.itemView.nama_task.text = currentItem.judul
        holder.itemView.deskripsi_task.text = currentItem.deskripsi
    }

    fun setData(note: List<Note>) {
        this.noteList = note
        notifyDataSetChanged()
    }

}