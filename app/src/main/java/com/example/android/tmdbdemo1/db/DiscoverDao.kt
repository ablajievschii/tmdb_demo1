package com.example.android.tmdbdemo1.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.tmdbdemo1.model.DiscoverMovie

@Dao
interface DiscoverDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<DiscoverMovie>)

    @Query("SELECT * FROM discover")
    fun getMovies(): PagingSource<Int, DiscoverMovie>

    @Query("SELECT * FROM discover WHERE id = :movieId")
    suspend fun getMovieById(movieId: Int): DiscoverMovie

    @Query("DELETE FROM discover")
    suspend fun clear()
}