package com.example.android.tmdbdemo1.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.android.tmdbdemo1.data.DiscoverMoviesRepository
import com.example.android.tmdbdemo1.data.GenreRepository
import com.example.android.tmdbdemo1.model.DiscoverMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repository: DiscoverMoviesRepository,
    private val repoGenre: GenreRepository
): ViewModel() {

    private val _movie = MutableSharedFlow<DiscoverMovie>()

    val title: LiveData<String?> = _movie.map {
        it.title
    }.asLiveData()
    val overview: LiveData<String?> = _movie.map {
        it.overview
    }.asLiveData()
    val posterPath: LiveData<String?> = _movie.map {
        it.posterPath
    }.asLiveData()

    fun loadMovie(movieId: Int) {
        viewModelScope.launch {
            val movie = repository.getMovie(movieId)
            _movie.emit(movie)
        }
    }
}
