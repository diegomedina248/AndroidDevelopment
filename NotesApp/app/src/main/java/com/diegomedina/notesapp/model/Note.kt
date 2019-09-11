package com.diegomedina.notesapp.model

data class Note(
    val id: Int,
    val content: String,
    val createdBy: String,
    val createdAt: String,
    val updatedAt: String
)
