package com.oscargil80.simplenotasmvvm

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("select * from  notesTable order by  id ASC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("select * from  notesTable where id = :id ")
    fun getById(id:Int): LiveData<Note>
}