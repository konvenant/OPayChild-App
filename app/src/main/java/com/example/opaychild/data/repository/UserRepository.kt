package com.example.opaychild.data.repository

import com.example.opaychild.data.models.UserPrefs

interface UserRepository  {
    fun getUser(): UserPrefs

    fun saveUser(userPrefs: UserPrefs): String
}