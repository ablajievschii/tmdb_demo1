package com.example.android.tmdbdemo1.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.android.tmdbdemo1.api.DiscoverMoviesPagingSource
import com.example.android.tmdbdemo1.model.DiscoverMovie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

const val PAGE_SIZE = 20

@Singleton
class DiscoverMoviesRepository @Inject constructor() {

    @Inject
    lateinit var pagingSource: DiscoverMoviesPagingSource

    fun discoverMoviesFlow(): Flow<PagingData<DiscoverMovie>> = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { pagingSource }
    ).flow
}