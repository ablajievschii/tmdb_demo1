package com.example.android.tmdbdemo1.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.android.tmdbdemo1.model.DiscoverMovie

class DiscoverMoviesPagingSource constructor(
    private val service: TMDBRestApi
) : PagingSource<Int, DiscoverMovie>() {
    override fun getRefreshKey(state: PagingState<Int, DiscoverMovie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DiscoverMovie> {
        val page = params.key ?: 1
        val response = service.getPopularMovies(page)
        val body = response.body()
        return if (response.isSuccessful && body != null) {
            val nextKey = if (body.page < body.totalPages) body.page + 1 else null
            LoadResult.Page(
                data = body.results ?: emptyList(),
                prevKey = null, // support only paging forward,
                nextKey = nextKey
            )
        } else {
            LoadResult.Error(RuntimeException("Failed to load movies for page $page"))
        }
    }
}