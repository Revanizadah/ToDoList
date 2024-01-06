package com.example.todolistapp.onboarding.screen

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import com.example.todolistapp.room.NoteViewModel
import kotlinx.android.synthetic.main.fragment_list_task.view.*

class ListTask : Fragment() {

    private lateinit var mNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_task, container, false)

        val mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)


        val adapter = ListAdapter(requireContext(), mNoteViewModel)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mNoteViewModel.readAllNote.observe(viewLifecycleOwner, Observer { note ->
            adapter.setData(note)
        })

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteAllNotes()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllNotes() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->
            mNoteViewModel.deleteAllNotes()
            Toast.makeText(requireContext(),"sukses", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_list_task)
        }
        builder.setNegativeButton("No"){_,_->}
        builder.setTitle("Delete ?")
        builder.setMessage("Yakin wirr ?")
        builder.create().show()
    }

}
