package com.example.opaychild.data.models

data class Notification(
    val type: NotificationType,
    val id: String,
    val isRead: Boolean,
    val date: Long,
    val desc: String,
    val title: String
)
