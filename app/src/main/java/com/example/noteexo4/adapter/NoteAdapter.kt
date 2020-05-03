package com.example.noteexo4.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.noteexo4.R
import com.example.noteexo4.data.db.entity.Note

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteHolder>() {




    companion object{
        private var noteList:List<Note> =ArrayList()
        lateinit var listner:OnItemClickListner
    }



    class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal val  text_view_id: TextView =itemView.findViewById(R.id.text_view_id)
        internal val  text_view_titre: TextView =itemView.findViewById(R.id.text_view_titre)
        internal val  text_view_date: TextView =itemView.findViewById(R.id.text_view_date)
        internal val  text_view_text: TextView =itemView.findViewById(R.id.text_view_text)
        internal val  layout: RelativeLayout =itemView.findViewById(R.id.item_background)

        init {
            itemView.setOnClickListener(View.OnClickListener {
                val position=adapterPosition
                if(position!= RecyclerView.NO_POSITION){

                    listner.onItemClick(noteList.get(position))
                }
            })
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_item,parent,false)

        return NoteHolder(itemView)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    fun setNote(noteListt:List<Note>)
    {
        noteList=noteListt
        notifyDataSetChanged()
    }

    fun getNoteAt(position: Int): Note? {
        return noteList[position]
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val currentNote: Note =noteList.get(position)
        holder.text_view_date.setText(currentNote.date)
        holder.text_view_titre.setText(currentNote.titre)
        holder.text_view_text.setText(currentNote.text)
        holder.text_view_id.setText(currentNote.numero.toString())

        holder.layout.setBackgroundColor(Color.parseColor("#"+currentNote.couleur))
    }

    interface OnItemClickListner{
        fun onItemClick(note: Note)
    }

    fun SetOnItemClickListner(listnerr:OnItemClickListner){
        listner=listnerr
    }
}