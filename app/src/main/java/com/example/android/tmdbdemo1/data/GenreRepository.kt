package com.example.android.tmdbdemo1.data

import com.example.android.tmdbdemo1.api.TMDBRestApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GenreRepository @Inject constructor(
    private val service: TMDBRestApi
) {

    // TODO could be stored in DB, it shouldn't change frequently
    private val genreById = mutableMapOf<Int, String>()

    fun genreById(id: Int): String? {
        return genreById[id]
    }

    suspend fun loadGenres() {
        val response = service.getGenres()
        response.body()?.genres?.forEach {
            genreById[it.id] = it.name
        }
    }
}