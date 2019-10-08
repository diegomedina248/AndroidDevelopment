package com.diegomedina.notesapp.controller

import com.diegomedina.notesapp.App
import com.diegomedina.notesapp.controller.adapter.ZonedDateTimeAdapter
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.threeten.bp.ZonedDateTime
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

object RetrofitController {
    var accessToken: String? = null

    val retrofit = Retrofit.Builder()
        .baseUrl("https://mighty-beyond-54626.herokuapp.com/")
        .addConverterFactory(gsonConverterFactory)
        .client(httpClient)
        .build()

    private val gsonConverterFactory
        get() = GsonConverterFactory.create(
            GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(ZonedDateTime::class.java, ZonedDateTimeAdapter())
                .create()
        )

    private val httpClient
        get() = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val response = chain.proceed(chain.request())

                if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                    App.goToLoginScreen()
                }

                response
            }
            .addInterceptor { chain ->
                if (accessToken != null) {
                    val request = chain.request()
                        .newBuilder()
                        .addHeader("Authorization", accessToken ?: "")
                        .build()

                    chain.proceed(request)
                } else {
                    chain.proceed(chain.request())
                }
            }
            .build()
}
