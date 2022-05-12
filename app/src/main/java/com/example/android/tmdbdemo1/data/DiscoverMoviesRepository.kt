package com.example.android.tmdbdemo1.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.android.tmdbdemo1.api.DiscoverMoviesPagingSource
import com.example.android.tmdbdemo1.api.TMDBRestApi
import com.example.android.tmdbdemo1.db.MoviesDatabase
import com.example.android.tmdbdemo1.model.DiscoverMovie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

const val PAGE_SIZE = 20

@Singleton
class DiscoverMoviesRepository @Inject constructor(
    private val service: TMDBRestApi,
    private val database: MoviesDatabase
) {

    fun discoverMoviesFlow(): Flow<PagingData<DiscoverMovie>> = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = false,
            initialLoadSize = 1
        ),
        pagingSourceFactory = { DiscoverMoviesPagingSource(service, database) }
    ).flow
}