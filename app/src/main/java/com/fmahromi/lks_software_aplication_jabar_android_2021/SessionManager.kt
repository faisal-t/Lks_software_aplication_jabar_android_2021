package com.fmahromi.lks_software_aplication_jabar_android_2021

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.fmahromi.lks_software_aplication_jabar_android_2021.model.ResponseUsers


class SessionManager(context: Context) {
    private val _context: Context
    private val sharedPreferences: SharedPreferences
    private val editor: SharedPreferences.Editor

    fun createLoginSession(user: ResponseUsers) {
        editor.putBoolean(IS_LOGGED_IN, true)
        editor.putString(USER_ID, user.data?.id)
        editor.putString(USERNAME, user.data?.username)
        editor.commit()
    }


    fun logoutSession() {
        editor.clear()
        editor.commit()
    }

    val isLoggedIn: Boolean
        get() = sharedPreferences.getBoolean(IS_LOGGED_IN, false)

    companion object {
        const val IS_LOGGED_IN = "isLoggedIn"
        const val USER_ID = "user_id"
        const val USERNAME = "username"

    }

    init {
        _context = context
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        editor = sharedPreferences.edit()
    }
}