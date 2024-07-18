package com.example.opaychild.data.models

data class Transactions(
    val desc: String,
    val transactionId: String,
    val date: Long,
    val status: Status ,
    val type: TransactionType,
)
