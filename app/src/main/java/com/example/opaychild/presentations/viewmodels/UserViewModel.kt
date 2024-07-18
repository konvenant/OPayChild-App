package com.example.opaychild.presentations.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.opaychild.data.models.SecurityCheck
import com.example.opaychild.data.models.User
import com.example.opaychild.data.models.UserPrefs
import com.example.opaychild.domain.GetUserUseCase

class UserViewModel(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    val userPref: MutableState<UserPrefs> = mutableStateOf<UserPrefs>(getUserPref())
    val user: MutableState<User> = mutableStateOf<User>(createDummyUser())

    val saveStatus: MutableState<String> = mutableStateOf<String>("")

    init {

    }

    fun getUserPref(): UserPrefs {
        return getUserUseCase.execute()
    }

     fun saveUserPref(userPrefs: UserPrefs){
           val result = getUserUseCase.saveUserPref(userPrefs)
            saveStatus.value = result
    }
}


fun createDummyUser(): User {
    return User(
        id = "dummy_user_id",
        accountNumber = 1234567890,
        accountName = "Dummy User",
        verified = true,
        password = "dummy_password",
        pin = 1234,
        dob = "01/01/1990", // Replace with a valid date format
        age = "30",
        connectedToParent = false,
        createdAt = System.currentTimeMillis(),
        lastModified = System.currentTimeMillis(),
        displayName = "Dummy User",
        favoriteColor = "Blue",
        friends = emptyList() // Or provide a list of dummy friends
    )
}