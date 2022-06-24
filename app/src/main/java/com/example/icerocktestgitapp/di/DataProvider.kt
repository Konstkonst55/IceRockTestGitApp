package com.example.icerocktestgitapp.di

import com.example.icerocktestgitapp.data.repository.AppRepository
import com.example.icerocktestgitapp.data.repository.IAuth
import com.example.icerocktestgitapp.data.repository.IReposDetails
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataProvider {

    @Singleton
    @Provides
    fun provideIReposDetails(appRepository: AppRepository): IReposDetails{
        return appRepository
    }

    @Singleton
    @Provides
    fun provideIAuth(appRepository: AppRepository): IAuth {
        return appRepository
    }
}
