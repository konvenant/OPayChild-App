package com.example.opaychild.data.dataSource

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.opaychild.data.dataSource.utils.constants
import com.example.opaychild.data.models.SecurityCheck
import com.example.opaychild.data.models.UserPrefs
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class UserPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(constants.PREFS_NAME, Context.MODE_PRIVATE)
    }



     fun getUserPrefs(): UserPrefs? {
        val json = sharedPreferences.getString("user_prefs", null) ?: return null
        return Json.decodeFromString(json)
    }

     fun saveUserPrefs(userPrefs: UserPrefs) {
        val json = Json.encodeToString(userPrefs)
        sharedPreferences.edit { putString("user_prefs", json) }
    }
}

