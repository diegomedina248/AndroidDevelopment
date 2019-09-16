package com.diegomedina.notesapp.view.home.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diegomedina.notesapp.R
import com.diegomedina.notesapp.model.Note
import kotlinx.android.synthetic.main.view_note.view.*

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {
    var notes = listOf<Note>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
//        val view = LayoutInflater.from(context)
//            .inflate(R.layout.view_note, parent, false)
//
//        return NoteViewHolder(view)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.view_note, parent, false)
            .let { view -> NoteViewHolder(view) }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: Note) {
            itemView.contentTextView.text = note.content
        }
    }
}
