package com.diegomedina.notesapp.data.controller

import com.diegomedina.notesapp.App
import com.diegomedina.notesapp.data.service.AuthService
import com.diegomedina.notesapp.data.service.request.LoginRequest

class AuthController {
    private val authService = RetrofitController.retrofit.create(AuthService::class.java)

    suspend fun login(email: String, password: String) {
        val request = LoginRequest(email, password)
        val response = authService.login(request)

        with(response.authToken) {
            RetrofitController.accessToken = this
            SharedPreferencesController.saveToken(this, App.currentActivity.get())
        }
    }

    suspend fun logout() {
        authService.logout()
        RetrofitController.accessToken = null
    }
}