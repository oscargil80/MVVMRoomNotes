package com.oscargil80.simplenotasmvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.oscargil80.simplenotasmvvm.databinding.ActivityAddEditNoteBinding
import java.text.SimpleDateFormat
import java.util.*

class AddEditNoteActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddEditNoteBinding
    lateinit var viewModel: NoteViewModel
    var noteID = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

        val noteType = intent.getStringExtra("noteType")
        if (noteType.equals("Edit")) {
            val noteId = intent.getIntExtra("noteID", 1)
            Toast.makeText(this, "Delete  $noteId", Toast.LENGTH_SHORT).show();
            viewModel.noteByID(noteId)

            //Toast.makeText(this, "Delete ${viewModel.titulo.value}", Toast.LENGTH_SHORT).show();
           // val noteTitle = intent.getStringExtra("noteTitle")
            val noteDesc = intent.getStringExtra("noteDescription")
            //noteID = intent.getIntExtra("noteID", -1)
            binding.btnAddUpdate.setText("Update Note")
            binding.etNoteTitle.setText(viewModel.titulo.value.toString())
            binding.etDescripcion.setText(noteDesc)
        } else {
            binding.btnAddUpdate.setText("Save Note")
        }

        binding.btnAddUpdate.setOnClickListener {
            val noteTitle = binding.etNoteTitle.text.toString()
            val noteDescripcion = binding.etDescripcion.text.toString()

            if (noteType.equals("Edit")) {

                if (noteTitle.isNotEmpty() && noteDescripcion.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDate: String = sdf.format(Date())
                    val updateNote = Note(noteTitle, noteDescripcion, currentDate)
                    updateNote.id = noteID
                    viewModel.updateNote(updateNote)
                    Toast.makeText(this, "Note Update....", Toast.LENGTH_SHORT).show();
                }
            } else {
                if (noteTitle.isNotEmpty() && noteDescripcion.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDate: String = sdf.format(Date())
                    viewModel.addNote(Note(noteTitle, noteDescripcion, currentDate))
                    Toast.makeText(this, "Note Added...", Toast.LENGTH_SHORT).show();
                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()

        }
    }
}