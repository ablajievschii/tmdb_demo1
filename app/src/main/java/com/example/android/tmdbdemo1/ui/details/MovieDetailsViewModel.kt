package com.example.android.tmdbdemo1.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(): ViewModel() {

    private val _overview = MutableSharedFlow<String>()
    val overview: LiveData<String> = _overview.asLiveData()

    private val _posterPath = MutableSharedFlow<String>()
    val posterPath: LiveData<String> = _posterPath.asLiveData()
}
