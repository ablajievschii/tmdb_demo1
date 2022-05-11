package com.example.android.tmdbdemo1.model

import com.google.gson.annotations.SerializedName

data class DiscoverMovie(
    val id: Int,
    val title: String? = null,
    val overview: String? = null,
    val popularity: Float? = null,
    @SerializedName(value = "release_date")
    val releaseData: String? = null,
    @SerializedName(value = "poster_path")
    val posterPath: String? = null
)
