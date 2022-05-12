package com.example.android.tmdbdemo1.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.room.withTransaction
import com.example.android.tmdbdemo1.db.MoviesDatabase
import com.example.android.tmdbdemo1.model.DiscoverMovie

/**
 * Could be replaced with RemoteMediator, but it is an experimental API
 */
class DiscoverMoviesPagingSource constructor(
    private val service: TMDBRestApi,
    private val database: MoviesDatabase
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
            val results = body.results ?: emptyList()

            if (results.isNotEmpty()) {
                // cache movies. NOTE currently it is not used for home page data, but that will be an improvement
                database.apply {
                    discoverDao().insertAll(results)
                }
            }

            LoadResult.Page(
                data = results,
                prevKey = null, // support only paging forward,
                nextKey = nextKey
            )
        } else {
            LoadResult.Error(RuntimeException("Failed to load movies for page $page"))
        }
    }
}