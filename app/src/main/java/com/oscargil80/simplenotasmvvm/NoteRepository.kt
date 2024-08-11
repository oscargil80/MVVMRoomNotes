package com.oscargil80.simplenotasmvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class NoteRepository(private val notesDao: NotesDao) {

    val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()

    suspend fun insert(note: Note) {
        notesDao.insert(note)
    }

    suspend fun delete(note: Note) {
        notesDao.delete(note)
    }

    suspend fun update(note: Note) {
        notesDao.update(note)
    }

   fun noteByID(id:Int): LiveData<Note> {
         return notesDao.getById(id)
    }

}


