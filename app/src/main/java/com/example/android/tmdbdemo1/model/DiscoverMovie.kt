package com.example.android.tmdbdemo1.model

import com.google.gson.annotations.SerializedName

data class DiscoverMovie(
    val id: Int,
    val title: String? = null,
    val overview: String? = null,
    val popularity: Float? = null,
    @SerializedName(value = "release_date")
    val releaseDate: String? = null,
    /**
     * "poster_sizes": [
        "w92",
        "w154",
        "w185",
        "w342",
        "w500",
        "w780",
        "original"
        ]
     */
    @SerializedName(value = "poster_path")
    val posterPath: String? = null,
    @SerializedName(value = "genre_ids")
    val genreIds: List<Int>? = null
)
