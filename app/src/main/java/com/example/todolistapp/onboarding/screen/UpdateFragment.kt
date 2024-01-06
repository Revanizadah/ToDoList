package com.example.todolistapp.onboarding.screen

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todolistapp.R
import com.example.todolistapp.room.Note
import com.example.todolistapp.room.NoteViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        view.update_nama_task.setText(args.currentNote.judul)
        view.update_deskripsi_task.setText(args.currentNote.deskripsi)
        view.update_date_task.setText(args.currentNote.date)


        view.submit_update.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem() {

        val judul = update_nama_task.text.toString()
        val deskripsi = update_deskripsi_task.text.toString()
        val date = update_date_task.text.toString()

        if (inputCheck(judul, deskripsi)){

            val updateNote = Note(args.currentNote.id, judul, deskripsi, date)

        mNoteViewModel.updateNote(updateNote)
        Toast.makeText(requireContext(), "Berhasil di Update!",  Toast.LENGTH_SHORT).show()

        findNavController().navigate(R.id.action_updateLayout_to_appbar)
    }else{
            Toast.makeText(requireContext(), "Pusing wirr!",  Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(nama_task:String, deskripsi_task:String): Boolean{
        return !(TextUtils.isEmpty(nama_task) || TextUtils.isEmpty(deskripsi_task))
    }

    @Deprecated("")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)

    }

    @Deprecated("")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete  ){
            deleteNote()
        }
        return super.onOptionsItemSelected(item)

    }

    private fun deleteNote() {

    }


}