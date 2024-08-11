package com.oscargil80.simplenotasmvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.oscargil80.simplenotasmvvm.databinding.ActivityMainBinding
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cargarRV() // Cargar el RecyclerView

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

        binding.fbAddNote.setOnClickListener {
            val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }
      }

    private fun onClickDelete(note: Note) {
        viewModel.deleteNote(note)
       Toast.makeText(this, "${note.noteTitle} Delete", Toast.LENGTH_SHORT).show();
    }

    private fun onClickListener(note: Note) {
        val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteID", note.id)
        startActivity(intent)
        this.finish()
    }

    private fun cargarRV() {
        binding.RVNotes.layoutManager = LinearLayoutManager(this)

        val noteRVAdapter = NoteAdapter(
            OnClickListener = { note -> onClickListener(note) },
            OnClickDelete = { note -> onClickDelete(note) })

        binding.RVNotes.adapter = noteRVAdapter

        viewModel.allNotes.observe(this) { list ->
            list?.let {
                noteRVAdapter.updateList(it)
            }
        }
    }
}
/*        var n = note.id
        viewModel.noteByID(n)
        viewModel.noteByID(n).observe(this, Observer {
            Toast.makeText(this, "Delete ${it.noteTitle}", Toast.LENGTH_SHORT).show();
        })*/