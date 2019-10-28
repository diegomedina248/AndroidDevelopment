package com.diegomedina.notesapp.inject

import android.preference.PreferenceManager
import com.diegomedina.notesapp.data.controller.AuthController
import com.diegomedina.notesapp.data.controller.RetrofitController
import com.diegomedina.notesapp.data.helper.adapter.ZonedDateTimeAdapter
import com.diegomedina.notesapp.data.repository.NotesSourceDataRepository
import com.diegomedina.notesapp.data.repository.NotesSourceRepository
import com.diegomedina.notesapp.data.repository.notes.NotesDataStoreFactory
import com.diegomedina.notesapp.data.service.NoteService
import com.diegomedina.notesapp.data.source.AppDatabase
import com.diegomedina.notesapp.presentation.view.home.notes.NotesViewModel
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.threeten.bp.ZonedDateTime
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var networkModule = module {
    single<GsonConverterFactory> {
        GsonConverterFactory.create(
            GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(ZonedDateTime::class.java, ZonedDateTimeAdapter())
                .create()
        )
    }

    single<Retrofit> { RetrofitController(get()).initRetrofit() }
    single<NoteService> { get<Retrofit>().create(NoteService::class.java) }
}

var databaseModule = module {
    single { AppDatabase.getInstance(get()).noteDao() }
}

var notesModule = module {
    single { NotesDataStoreFactory(get(), get()) }
    single<NotesSourceRepository> { NotesSourceDataRepository(get()) }

    viewModel { NotesViewModel(get()) }
}

var loginModule = module {
    single { PreferenceManager.getDefaultSharedPreferences(get()) }
    single { AuthController() }
}