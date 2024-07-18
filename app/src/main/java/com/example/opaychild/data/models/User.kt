package com.example.opaychild.data.models

import android.graphics.Bitmap

data class User(
    val id: String,
    val accountNumber: Int,
    val accountName: String,
    val verified: Boolean,
    val image: Bitmap? = null,
    val password: String,
    val pin: Int,
    val dob: String,
    val age: String,
    val connectedToParent: Boolean,
    val parentAccountNumber: Int? = null,
    val createdAt: Long,
    val lastModified: Long,
    val accountBalance: Double = 0.0,
    val tier: Tier = Tier.DREAMERS,
    val displayName: String,
    val phoneNumber: Int? = null,
    val email: String? = null,
    val favoriteColor: String,
    val friends: List<Friend>
    )
