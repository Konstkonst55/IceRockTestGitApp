package com.example.icerocktestgitapp.data.storage

import android.content.Context
import com.example.icerocktestgitapp.utils.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class KeyValueStorage @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private val prefs = context.getSharedPreferences(Constants.PREFS_NAME_KEY, Context.MODE_PRIVATE)

    var authToken: String?
        get() = prefs.getString(Constants.TOKEN_KEY, null)
        set(token) {
            prefs.edit().putString(Constants.TOKEN_KEY, token).apply()
        }

    var userName: String?
        get() = prefs.getString(Constants.USER_KEY, null)
        set(name){
            prefs.edit().putString(Constants.USER_KEY, name).apply()
        }

    fun clearData(){
        prefs.edit().clear().apply()
    }
}