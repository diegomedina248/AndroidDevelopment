package com.diegomedina.notesapp.service

import com.diegomedina.notesapp.model.Note
import retrofit2.http.GET

interface NoteService {
    @GET("notes/all")
    suspend fun getAllNotes(): List<Note>
}
