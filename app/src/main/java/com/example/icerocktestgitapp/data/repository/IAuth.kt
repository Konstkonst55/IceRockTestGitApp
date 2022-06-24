package com.example.icerocktestgitapp.data.repository

import com.example.icerocktestgitapp.data.resources.Resource

interface IAuth {
    suspend fun signIn(token: String? = null): Resource<Unit>
    suspend fun signOut()
}