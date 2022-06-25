package com.example.icerocktestgitapp.data.network.interceptor

import com.example.icerocktestgitapp.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AcceptInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .header(Constants.HEADER_ACCEPT_NAME, Constants.HEADER_ACCEPT_VALUE)
            .build()
        return chain.proceed(request)
    }
}