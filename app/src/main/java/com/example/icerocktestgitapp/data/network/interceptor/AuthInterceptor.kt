package com.example.icerocktestgitapp.data.network.interceptor

import com.example.icerocktestgitapp.data.storage.KeyValueStorage
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val storage: KeyValueStorage) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = storage.authToken
        val request = chain.request().newBuilder()
            .header("Authorization", "token $token")
            .build()
        return chain.proceed(request)
    }
}