package com.example.android.tmdbdemo1.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.android.tmdbdemo1.data.converter.ListOfIntegerConverter
import com.example.android.tmdbdemo1.model.DiscoverMovie

@Database(
    entities = [DiscoverMovie::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ListOfIntegerConverter::class)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun discoverDao(): DiscoverDao
}