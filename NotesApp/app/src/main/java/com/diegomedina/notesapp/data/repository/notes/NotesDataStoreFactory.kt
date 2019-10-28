package com.diegomedina.notesapp.data.repository.notes

import com.diegomedina.notesapp.data.dao.NoteDao
import com.diegomedina.notesapp.data.service.NoteService

class NotesDataStoreFactory(var service: NoteService, var dao: NoteDao) {

    private var condition = true

    var notesDataStoreFactory: NotesDataStore
        get() {
            return createDataSourceFactory()
        }
        set(value) {}

    private fun createDataSourceFactory() = if (condition) {
        CloudNotesDataStore(service)
    } else {
        DatabaseNotesDataStore(dao)
    }
}