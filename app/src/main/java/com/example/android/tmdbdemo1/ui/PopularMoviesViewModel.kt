package com.example.android.tmdbdemo1.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.android.tmdbdemo1.data.DiscoverMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val repository: DiscoverMoviesRepository
): ViewModel() {

    val pagingData: Flow<PagingData<UiModel>>

    /**
     * UI Action processor
     */
    val accept: (UiAction) -> Unit

    init {
        // action router
        val sharedFlow = MutableSharedFlow<UiAction>()

        pagingData = sharedFlow
            .filterIsInstance<UiAction.LoadMore>()
            .distinctUntilChanged()
            .flatMapLatest {
                loadMovies()
            }
            .cachedIn(viewModelScope) // avoid data re-fetch on configuration change

        accept = { action ->
            viewModelScope.launch {
                sharedFlow.emit(action)
            }
        }
    }

    private fun loadMovies(): Flow<PagingData<UiModel>> {
        return repository.discoverMoviesFlow().map { pagingData ->
            pagingData.map { UiModel.DiscoverMovieItem(it) }
        }
    }
}

sealed class UiAction {
    class LoadMore() : UiAction()
}