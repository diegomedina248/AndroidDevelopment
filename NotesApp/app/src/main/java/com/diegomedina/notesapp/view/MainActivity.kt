package com.diegomedina.notesapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.diegomedina.notesapp.R
import com.diegomedina.notesapp.controller.NoteController
import com.diegomedina.notesapp.helper.gone
import com.diegomedina.notesapp.helper.visible
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    private val adapter = NotesAdapter(this)
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val controller = NoteController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = adapter

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = adapter
        }

        getNotes()
    }

    private fun getNotes() {
        progressBar.visible()
        recyclerView.gone()

        launch(Dispatchers.IO) {
            val notes = controller.getAllNotes()
            withContext(Dispatchers.Main) {
                adapter.notes = notes

                progressBar.gone()
                recyclerView.visible()
            }
        }
    }
}
