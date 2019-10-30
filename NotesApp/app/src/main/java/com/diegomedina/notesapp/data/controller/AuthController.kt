package com.diegomedina.notesapp.data.controller

import com.diegomedina.notesapp.App
import com.diegomedina.notesapp.data.service.AuthService
import com.diegomedina.notesapp.data.service.request.LoginRequest

class AuthController(
    private val authService: AuthService,
    private val retrofitController: RetrofitController
) {
    suspend fun login(email: String, password: String) {
        val request = LoginRequest(email, password)
        val response = authService.login(request)

        with(response.authToken) {
            retrofitController.accessToken = this
            SharedPreferencesController.saveToken(this, App.currentActivity.get())
        }
    }

    suspend fun logout() {
        authService.logout()
        retrofitController.accessToken = null
    }
}
