package com.example.android.tmdbdemo1.ui

import com.example.android.tmdbdemo1.model.DiscoverMovie

// There might be more than one item type, which will represent different RecyclerView list items
sealed class UiModel {
    data class DiscoverMovieItem(
        val movie: DiscoverMovie,
        val genres: List<String>? = null
    ) : UiModel()
}