package com.example.opaychild.domain

import com.example.opaychild.data.models.UserPrefs
import com.example.opaychild.data.repository.UserRepository

class GetUserUseCase(
    private val userRepository: UserRepository
) {
    fun execute(): UserPrefs{
      return userRepository.getUser()
    }

    fun saveUserPref(userPrefs: UserPrefs) : String{
        return userRepository.saveUser(userPrefs)
    }
}