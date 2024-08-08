package com.oscargil80.simplenotasmvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class NoteAdapter(
     private val OnClickListener:(Note) ->Unit,
    private val OnClickDelete:(Note) ->Unit
) : RecyclerView.Adapter<NoteViewHolder>() {

    private val allNotes = ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NoteViewHolder(layoutInflater.inflate(R.layout.note_rv_item, parent, false))
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = allNotes[position]
        holder.render(item, OnClickListener, OnClickDelete)
    }

    override fun getItemCount(): Int = allNotes.size

    fun updateList(newList:List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

}


