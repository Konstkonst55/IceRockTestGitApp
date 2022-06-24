package com.example.icerocktestgitapp.di

import com.example.icerocktestgitapp.data.network.IGitApiService
import com.example.icerocktestgitapp.data.network.interceptor.AcceptInterceptor
import com.example.icerocktestgitapp.data.network.interceptor.AuthInterceptor
import com.example.icerocktestgitapp.data.storage.KeyValueStorage
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
    private val baseUrl = "https://api.github.com"
    private val json = Json { ignoreUnknownKeys = true }
    private val mediaType = MediaType.get("application/json; charset=utf-8")

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

//    @ExperimentalSerializationApi
//    @Singleton
//    @Provides
//    fun provideApi() : IGitApiService =
//        Retrofit.Builder()
//            .baseUrl(baseUrl)
//            .addConverterFactory(json.asConverterFactory(mediaType))
//            .build()
//            .create(IGitApiService::class.java)

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

        val contentType = MediaType.get("application/json")
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(client)
            .build()

        return retrofit.create(IGitApiService::class.java)
    }
}