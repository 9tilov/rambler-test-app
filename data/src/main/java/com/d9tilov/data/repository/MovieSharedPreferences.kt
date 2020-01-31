package com.d9tilov.data.repository

import android.content.SharedPreferences

class MovieSharedPreferences(private val sharedPreferences: SharedPreferences) {

    companion object {
        private const val PREF_KEY_LAST_UPDATE_TIME = "last_update_time"
    }

    fun saveLastUpdateTime(time: Long) {
        val editor = sharedPreferences.edit()
        editor.putLong(PREF_KEY_LAST_UPDATE_TIME, time)
        editor.apply()
    }

    fun loadLastUpdateTime(): Long {
        return sharedPreferences.getLong(PREF_KEY_LAST_UPDATE_TIME, System.currentTimeMillis())
    }
}
