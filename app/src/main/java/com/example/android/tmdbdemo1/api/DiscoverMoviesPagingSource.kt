package com.example.android.tmdbdemo1.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.android.tmdbdemo1.model.DiscoverMovie
import javax.inject.Inject

class DiscoverMoviesPagingSource @Inject constructor(
) : PagingSource<Int, DiscoverMovie>() {
    override fun getRefreshKey(state: PagingState<Int, DiscoverMovie>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DiscoverMovie> {
        TODO("Not yet implemented")
    }
}