package com.diegomedina.notesapp.data.repository.notes

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService
import com.diegomedina.notesapp.data.dao.NoteDao
import com.diegomedina.notesapp.data.service.NoteService

@Suppress("UNUSED_PARAMETER")
class NotesDataStoreFactory(var context: Context, var service: NoteService, var dao: NoteDao) {

    private fun isNetworkOnline(): Boolean {
        val connectivityManager =
            getSystemService(context, CONNECTIVITY_SERVICE::class.java) as ConnectivityManager?
        connectivityManager?.let {
            return connectivityManager.isDefaultNetworkActive
        }

        return false
    }

    var notesDataStoreFactory: NotesDataStore
        get() {
            return createDataSourceFactory()
        }
        set(value) {}

    private fun createDataSourceFactory() = if (isNetworkOnline()) {
        CloudNotesDataStore(service)
    } else {
        DatabaseNotesDataStore(dao)
    }
}