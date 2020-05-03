package com.example.noteexo4.ui.noteAddEdit

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

import com.example.noteexo4.R
import com.example.noteexo4.data.db.entity.Note
import kotlinx.android.synthetic.main.note_add_edit_fragment.*
import java.text.SimpleDateFormat
import java.util.*

class NoteAddEdit : Fragment() {

    private lateinit var noteAddEditViewModel: NoteAddEditViewModel
    private lateinit var noteAddViewModelFactory: NoteAddEditViewModelFactory

    private lateinit var sfeArgs:NoteAddEditArgs

    var color="FFFFFF"
    companion object {
        fun newInstance() = NoteAddEdit()
    }

    private lateinit var viewModel: NoteAddEditViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.note_add_edit_fragment, container, false)
    }

    @SuppressLint("ResourceAsColor")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)


        noteAddViewModelFactory= NoteAddEditViewModelFactory(activity!!.application)
        noteAddEditViewModel=ViewModelProviders.of(this,noteAddViewModelFactory).get(
            NoteAddEditViewModel::class.java)

        getSafeArgs(arguments!!)

        btn_blue.setOnClickListener {
            //Toast.makeText(activity,"blue", Toast.LENGTH_SHORT).show()
            color="00BCD4"
            add_layout.setBackgroundColor(Color.parseColor("#00BCD4"))
        }
        btn_green.setOnClickListener {
            color="4CAF50"
            add_layout.setBackgroundColor(Color.parseColor("#4CAF50"))
        }
        btn_orange.setOnClickListener {
            color="C56547"
            add_layout.setBackgroundColor(Color.parseColor("#C56547"))
        }
        btn_yellow.setOnClickListener {
            color="FFEB3B"
            add_layout.setBackgroundColor(Color.parseColor("#FFEB3B"))
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
        inflater.inflate(R.menu.save_intervention_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save_intervention -> {
                saveIntervention()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun saveIntervention() {

        val titre :String = edit_view_titre.text.toString()
        val text :String  = edit_view_text.text.toString()

        val sdf = SimpleDateFormat("yy-MM-dd")
        val date: String = sdf.format(Date(datePicker.year, datePicker.month , datePicker.dayOfMonth))



        if(titre.trim().isEmpty() ||text.trim().isEmpty()){
            Toast.makeText(activity,"Remplir tous les champs", Toast.LENGTH_SHORT).show()
        }else{

            val id=sfeArgs.id
            if(id!=0){

                val note = Note(date,titre,text,color)
                note.setId(id)
                noteAddEditViewModel.modifier(note)
                Toast.makeText(activity, "note modifier", Toast.LENGTH_SHORT).show()

            }else{

                val note = Note(date,titre,text,color)
                noteAddEditViewModel.ajouter(note)
                Toast.makeText(activity, "note ajoute", Toast.LENGTH_SHORT).show()
            }

            view!!.findNavController().navigate(R.id.action_noteAddEdit_to_note_list_fragment)
        }

    }

    private fun getSafeArgs(bundle: Bundle){
        sfeArgs=NoteAddEditArgs.fromBundle(bundle)

        if(sfeArgs.id!=0)
        {
            edit_view_titre.setText(sfeArgs.titre)
            edit_view_text.setText(sfeArgs.text)

            val date=sfeArgs.date
            datePicker.init(("20"+date.substring(0,2)).toInt(),date.substring(3,5).toInt()-1,date.substring(6,8).toInt(),null)

            add_layout.setBackgroundColor(Color.parseColor("#"+sfeArgs.color))
        }



    }

}
