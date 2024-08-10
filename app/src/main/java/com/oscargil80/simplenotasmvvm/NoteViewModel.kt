package com.oscargil80.simplenotasmvvm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteViewModel(application: Application): AndroidViewModel(application) {

    val allNotes:LiveData<List<Note>>
   // var id = MutableLiveData<Long>()
    var titulo = MutableLiveData<String>()
    var descripcion = MutableLiveData<String>()
    val repository:NoteRepository

    init {
        val dao = NoteDatabase.getDatabase(application).getNotesDao()
        repository =NoteRepository(dao)
        allNotes = repository.allNotes
    }

    fun  deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun  noteByID(id: Int) {
        viewModelScope.launch {
               var notas =  withContext(Dispatchers.IO){
                 repository.noteByID(id)
            }
            titulo.value = notas.noteTitle
        }
    }


    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }

    fun addNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

   /* fun noteByID(id:Int): Note {
        return repository.noteByID(id)
    }*/




}