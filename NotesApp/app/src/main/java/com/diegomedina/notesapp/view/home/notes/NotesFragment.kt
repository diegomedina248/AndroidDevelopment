package com.diegomedina.notesapp.view.home.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.diegomedina.notesapp.R
import com.diegomedina.notesapp.controller.NoteController
import com.diegomedina.notesapp.helper.gone
import com.diegomedina.notesapp.helper.visible
import com.diegomedina.notesapp.helper.visibleIf
import com.diegomedina.notesapp.model.Note
import kotlinx.android.synthetic.main.fragment_notes.*
import kotlinx.coroutines.*
import retrofit2.HttpException
import kotlin.coroutines.CoroutineContext

class NotesFragment : Fragment() {
    private val adapter = NotesAdapter()
    private val viewModel = NotesViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_notes, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = this@NotesFragment.adapter
        }

        viewModel.loadNotes()
        viewModel.notes.observe(viewLifecycleOwner, Observer(this::notesLoaded))
        viewModel.isLoading.observe(viewLifecycleOwner, Observer(this::loadingStateChanged))
    }

    private fun notesLoaded(notes: List<Note>) {
        adapter.notes = notes
    }

    private fun loadingStateChanged(isLoading: Boolean) {
        progressBar.visibleIf(isLoading)
        recyclerView.visibleIf(!isLoading)
    }
}
