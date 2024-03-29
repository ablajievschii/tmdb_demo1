package com.example.android.tmdbdemo1.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.android.tmdbdemo1.data.DiscoverMoviesRepository
import com.example.android.tmdbdemo1.data.GenreRepository
import com.example.android.tmdbdemo1.model.DiscoverMovie
import com.example.android.tmdbdemo1.model.Movie
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

    private val _discoverMovie = MutableSharedFlow<DiscoverMovie>()
    private val _movie = MutableSharedFlow<Movie>()

    val title: LiveData<String?> = _discoverMovie.map {
        it.title
    }.asLiveData()
    val overview: LiveData<String?> = _discoverMovie.map {
        it.overview
    }.asLiveData()
    val posterPath: LiveData<String?> = _discoverMovie.map {
        it.posterPath
    }.asLiveData()
    val genres: LiveData<List<String>?> = _discoverMovie.map {
        it.genreIds?.mapNotNull { id -> repoGenre.genreById(id) }
    }.asLiveData()
    val homepage: LiveData<String?> = _movie.map {
        it.homepage
    }.asLiveData()
    val votes: LiveData<Float?> = _discoverMovie.map {
        it.voteAverage
    }.asLiveData()

    fun loadMovie(movieId: Int) {
        viewModelScope.launch {
            val movie = repository.getMovie(movieId)
            _discoverMovie.emit(movie)
        }
        viewModelScope.launch {
            repository.enhanceMovie(movieId)?.let {
                _movie.emit(it)
            }
        }
    }
}
