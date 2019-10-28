package com.diegomedina.notesapp

import com.diegomedina.notesapp.presentation.view.home.notes.NotesViewModel
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun exampleMock() {
        var viewmodel = NotesViewModel(MockNotesDataSourceRepository())
        viewmodel.loadNotes()
    }
}
