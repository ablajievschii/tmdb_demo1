package com.example.android.tmdbdemo1.api

import com.example.android.tmdbdemo1.api.DiscoverMovieResponse
import retrofit2.http.GET

// /discover/movie
// /movie/{movie_id}
// /genre/movie/list
interface TMDBRestApi {

    @GET("/discover/movie")
    suspend fun getPopularMovies(): DiscoverMovieResponse
}