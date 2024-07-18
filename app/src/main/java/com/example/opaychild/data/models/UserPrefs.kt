package com.example.opaychild.data.models

data class UserPrefs(
    val isLoggedIn: Boolean,
    val securityType: SecurityCheck = SecurityCheck.NONE,
    val isFirstTime: Boolean,
)