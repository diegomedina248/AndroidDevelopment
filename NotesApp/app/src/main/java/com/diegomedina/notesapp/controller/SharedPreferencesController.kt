package com.diegomedina.notesapp.controller

import android.content.Context
import android.preference.PreferenceManager

object SharedPreferencesController {

    private const val accessTokenKey = "AccessToken"

    fun getToken(context: Context): String? =
        PreferenceManager
            .getDefaultSharedPreferences(context)
            .getString(accessTokenKey, null)

    fun saveToken(authToken: String, context: Context?) {
        PreferenceManager
            .getDefaultSharedPreferences(context)
            .edit()
            .putString(accessTokenKey, authToken)
            .apply()
    }

    fun removeToken(context: Context) {
        PreferenceManager
            .getDefaultSharedPreferences(context)
            .edit()
            .remove(accessTokenKey)
            .apply()
    }
}