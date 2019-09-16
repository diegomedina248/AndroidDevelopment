package com.diegomedina.notesapp.controller

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
                .create()
        )

    private val httpClient
        get() = OkHttpClient.Builder()
            .addInterceptor { chain ->
                if (accessToken != null) {
                    val request = chain.request()
                        .newBuilder()
                        .addHeader("Authorization", accessToken)
                        .build()

                    chain.proceed(request)
                } else {
                    chain.proceed(chain.request())
                }
            }
            .build()
}
