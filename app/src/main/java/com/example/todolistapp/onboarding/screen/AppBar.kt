package com.example.todolistapp.onboarding.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todolistapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.TextUtils
import android.view.Gravity
import android.view.Window
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todolistapp.room.Note
import com.example.todolistapp.room.NoteViewModel
import com.google.android.material.textfield.TextInputEditText


class AppBar : Fragment() {
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var navview: BottomNavigationView
    private lateinit var plusButton: FloatingActionButton
    private lateinit var bottomSheet: View
    private lateinit var inputNamaTask: TextInputEditText
    private lateinit var inputDeskripsiTask: TextInputEditText




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.appbar, container, false)

        navview = view.findViewById(R.id.nav_view)
        plusButton = view.findViewById(R.id.plus_button)

        replaceFragment(ListTask())

        navview.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_notes -> replaceFragment(ListTask())
                R.id.nav_done -> replaceFragment(CalendarFragment())
            }
            true
        }


        return view
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.appbar, fragment)
        fragmentTransaction.commit()
    }
//    private fun navigateToCalendarActivity() {
//        val intent = Intent(requireContext(), CalendarActivity::class.java)
//        startActivity(intent)
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        bottomSheet = view.findViewById(R.id.plus_button)
        bottomSheet.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {

        context?.let { ctx ->
            val dialog = Dialog(ctx)

            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.fragment_bottom_sheet)

            inputNamaTask = dialog.findViewById(R.id.add_nama_task)
            inputDeskripsiTask = dialog.findViewById(R.id.add_deskripsi_task)
//            val datePicker = dialog.findViewById<DatePicker>(R.id.datePicker)
            val submit = dialog.findViewById<Button>(R.id.submit)

            noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

            submit.setOnClickListener {
                insertNote()
                dialog.dismiss()
            }


            dialog.show()
            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
            dialog.window?.setGravity(Gravity.BOTTOM)
        }
    }

    private fun insertNote() {

        val namatask = inputNamaTask.text.toString()
        val deskripsitask = inputDeskripsiTask.text.toString()

        if(inputCheck(namatask, deskripsitask)){
            val note = Note(0, namatask, deskripsitask)

            noteViewModel.addNote(note)
            Toast.makeText(requireContext(), "Success", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.appbarFragment)

        } else {
            Toast.makeText(requireContext(), "isi form nya wir", Toast.LENGTH_LONG).show()

        }

    }

    private fun inputCheck(nama_task:String, deskripsi_task:String): Boolean{
        return !(TextUtils.isEmpty(nama_task) || TextUtils.isEmpty(deskripsi_task))
    }
}
