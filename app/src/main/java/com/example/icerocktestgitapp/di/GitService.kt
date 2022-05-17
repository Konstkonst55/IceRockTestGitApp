package com.example.icerocktestgitapp.di

import android.provider.MediaStore
import com.example.icerocktestgitapp.data.network.IGitApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class GitService {
    private val baseUrl = ""
    private val json = Json { ignoreUnknownKeys = true }
    private val mediaType = MediaType.get("application/json; charset=utf-8")
    @ExperimentalSerializationApi
    @Singleton
    @Provides
    fun provideApi() : IGitApiService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(json.asConverterFactory(mediaType))
            .build()
            .create(IGitApiService::class.java)
}