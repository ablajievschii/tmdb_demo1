package com.example.android.tmdbdemo1.api

import com.example.android.tmdbdemo1.model.DiscoverMovie
import com.google.gson.annotations.SerializedName

data class DiscoverMovieResponse(
    val page: Int,
    @SerializedName(value = "total_results")
    val totalResults: Int,
    @SerializedName(value = "total_pages")
    val totalPages: Int,
    val results: List<DiscoverMovie>? = null
)