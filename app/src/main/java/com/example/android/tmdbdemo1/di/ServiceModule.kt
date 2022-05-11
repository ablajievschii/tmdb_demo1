package com.example.android.tmdbdemo1.di

import com.example.android.tmdbdemo1.api.TMDBRestApi
import com.example.android.tmdbdemo1.api.TMDBServiceBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ServiceModule {

    @Singleton
    @Provides
    fun provideDiscoverRestApi(): TMDBRestApi {
        return TMDBServiceBuilder
            .buildService(TMDBRestApi::class.java)
    }
}