package com.example.android.tmdbdemo1.api

import com.example.android.tmdbdemo1.BuildConfig
import com.example.android.tmdbdemo1.api.DiscoverMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// /discover/movie
// /movie/{movie_id}
// /genre/movie/list
interface TMDBRestApi {

    /**
     * @param page minimum: 1; maximum: 1000; default: 1
     */
    @GET("discover/movie")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): Response<DiscoverMovieResponse>
}

fun tmdbImageUrl(path: String, size: String): String {
    return BuildConfig.TMDB_IMAGE_BASE_URL + size + "/" + path
}