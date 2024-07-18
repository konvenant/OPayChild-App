package com.example.opaychild.data.models

data class Story(
    val id: String,
    val title: String,
    val desc: String,
    val images: List<Int>,
    val date: String,
    val tags: List<String>,
    val rating: Int,
    val likes: Int,
    val comments: List<Triple<String,String,Long>>,
    val story: String? = null
)
