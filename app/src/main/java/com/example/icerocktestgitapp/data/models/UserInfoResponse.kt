package com.example.icerocktestgitapp.data.models

import kotlinx.serialization.Serializable

@Serializable
data class UserInfoResponse(
    val login: String
)