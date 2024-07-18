package com.example.opaychild.data.repository

import com.example.opaychild.data.dataSource.UserPreferences
import com.example.opaychild.data.models.SecurityCheck
import com.example.opaychild.data.models.UserPrefs

class UserRepositoryImpl(private val userPreferences: UserPreferences) : UserRepository {
    override fun getUser(): UserPrefs {
        val initialUserPrefs = UserPrefs(
            isLoggedIn = false,
            securityType = SecurityCheck.NONE,
            isFirstTime = true
        )
       return  userPreferences.getUserPrefs() ?: initialUserPrefs
    }

    override fun saveUser(userPrefs: UserPrefs): String {
        return try {
            userPreferences.saveUserPrefs(userPrefs)
            "Success"
        } catch (e:Exception){
            e.message.toString()
        }
    }
}