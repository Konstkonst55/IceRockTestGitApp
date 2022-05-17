package com.example.icerocktestgitapp.data.network

import com.example.icerocktestgitapp.data.models.UserInfo
import com.example.icerocktestgitapp.data.models.UserInfoResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface IGitApiService {
    @GET("/user")
    suspend fun signIn(
        @Header("header") header : String
    ): UserInfoResponse
}