package com.diegomedina.notesapp.controller

import com.diegomedina.notesapp.service.NoteService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NoteController {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://mighty-beyond-54626.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val noteService = retrofit.create(NoteService::class.java)

    suspend fun getAllNotes() = noteService.getAllNotes()
}
