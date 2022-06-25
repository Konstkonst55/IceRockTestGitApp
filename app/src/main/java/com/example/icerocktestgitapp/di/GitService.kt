package com.example.icerocktestgitapp.di

import com.example.icerocktestgitapp.data.network.IGitApiService
import com.example.icerocktestgitapp.data.network.interceptor.AcceptInterceptor
import com.example.icerocktestgitapp.data.network.interceptor.AuthInterceptor
import com.example.icerocktestgitapp.data.storage.KeyValueStorage
import com.example.icerocktestgitapp.utils.Constants
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class GitService {
    @AuthInterceptorOkHttpClient
    @Singleton
    @Provides
    fun providesAuthInterceptor(storage: KeyValueStorage): Interceptor = AuthInterceptor(storage)

    @AcceptInterceptorOkHttpClient
    @Singleton
    @Provides
    fun providesAcceptInterceptor(): Interceptor = AcceptInterceptor()

    @Singleton
    @Provides
    fun provideOkHttpClientWithInterceptors(
        @AcceptInterceptorOkHttpClient acceptInterceptor: Interceptor,
        @AuthInterceptorOkHttpClient authInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(acceptInterceptor)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideJson(): Json{
        return Json{
            ignoreUnknownKeys = true
        }
    }

    @Singleton
    @Provides
    @kotlinx.serialization.ExperimentalSerializationApi
    fun provideAPIService(client: OkHttpClient, json: Json): IGitApiService {
        val contentType = MediaType.get(Constants.MEDIA_TYPE)
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(client)
            .build()

        return retrofit.create(IGitApiService::class.java)
    }
}