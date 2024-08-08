package com.oscargil80.simplenotasmvvm

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.oscargil80.simplenotasmvvm.databinding.NoteRvItemBinding

class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {

private  var binding = NoteRvItemBinding.bind(view)

    fun render(
        Note: Note,
        OnClickListener: (Note) -> Unit,
        OnClickDelete: (Note) -> Unit
    ) {
        binding.tvNoteTitle.text = Note.noteTitle
        binding.tvTimeStamp.text =       "Last Update : ${Note.timeStamp}"

        binding.ivDelete.setOnClickListener {
            OnClickDelete(Note)
        }

        itemView.setOnClickListener {
            OnClickListener(Note)
        }
        }




    }
