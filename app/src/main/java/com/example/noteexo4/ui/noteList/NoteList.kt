package com.example.noteexo4.ui.noteList

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.noteexo4.R
import com.example.noteexo4.adapter.NoteAdapter
import com.example.noteexo4.data.db.entity.Note
import kotlinx.android.synthetic.main.note_list_fragment.*

class NoteList : Fragment() {

    private lateinit var noteListViewModel: NoteListViewModel
    private lateinit var noteListViewModelFactory: NoteListViewModelFactory

    companion object {
        fun newInstance() =
            NoteList()
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.note_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)

        add_note.setOnClickListener{
            view!!.findNavController().navigate(R.id.action_note_list_fragment_to_noteAddEdit)
        }


        val recyclerView: RecyclerView = recycler_view as RecyclerView
        recyclerView.layoutManager= LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)

        val adapter= NoteAdapter()
        recyclerView.adapter=adapter

        noteListViewModelFactory=
            NoteListViewModelFactory(
                activity!!.application
            )
        noteListViewModel=ViewModelProviders.of(this,noteListViewModelFactory).get(
            NoteListViewModel::class.java)





        noteListViewModel.getAllNote().observe(viewLifecycleOwner,
            Observer { note -> adapter.setNote(note  as List<Note>) })



        //supprimer l'intervention on swipe
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(//Drag and drop methode
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.getNoteAt(viewHolder.adapterPosition)?.let {
                    noteListViewModel.supprimer(
                        it
                    )
                }
                Toast.makeText(activity, "note suprimer", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(recyclerView)

        adapter.SetOnItemClickListner(object : NoteAdapter.OnItemClickListner{
            override fun onItemClick(note: Note) {

                val nextAction=NoteListDirections.actionNoteListFragmentToNoteAddEdit()
                nextAction.setTitre(note.titre)
                nextAction.setText(note.text)
                nextAction.setDate(note.date)
                nextAction.setColor(note.couleur)
                nextAction.setId(note.numero)

                Navigation.findNavController(view!!).navigate(nextAction)

            }

        })
    }

}
