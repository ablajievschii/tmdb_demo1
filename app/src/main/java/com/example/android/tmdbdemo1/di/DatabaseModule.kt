package com.example.android.tmdbdemo1.di

import android.content.Context
import androidx.room.Room
import com.example.android.tmdbdemo1.db.DiscoverDao
import com.example.android.tmdbdemo1.db.MoviesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun provideDiscoverDao(database: MoviesDatabase): DiscoverDao {
        return database.discoverDao()
    }

    @Provides
    @Singleton
    fun provideMoviesDatabase(@ApplicationContext context: Context): MoviesDatabase {
        return Room.databaseBuilder(
            context,
            MoviesDatabase::class.java,
            "TMDB Movies Database",
        ).build()
    }
}