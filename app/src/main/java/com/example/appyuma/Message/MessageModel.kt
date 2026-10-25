package com.example.appyuma.Message

data class MessageModel(
    val senderName: String,
    val messageText: String,
    val avatarUrl: String,
    val time: String = ""
)
