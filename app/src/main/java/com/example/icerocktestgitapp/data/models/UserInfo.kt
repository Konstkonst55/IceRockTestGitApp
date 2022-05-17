package com.example.icerocktestgitapp.data.models

data class UserInfo (
    val userName: String
)

fun UserInfoResponse.convertToUserInfo(): UserInfo{
    return UserInfo(this.login)
}