package com.d121211015.topratedmovieapp.ui.activities.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.d121211015.topratedmovieapp.MyApplication
import com.d121211015.topratedmovieapp.data.models.TopMovie
import com.d121211015.topratedmovieapp.data.repository.MovieRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MainUiState {
    data class Success(val movies: List<TopMovie>) : MainUiState
    object Error : MainUiState
    object Loading : MainUiState
}

class MainViewModel(private val moviesRepository: MovieRepository): ViewModel() {

    // initial state
    var mainUiState: MainUiState by mutableStateOf(MainUiState.Loading)
        private set

    fun getMovies() = viewModelScope.launch {
        mainUiState = MainUiState.Loading
        try {
            val result = moviesRepository.getMovies()
            mainUiState = MainUiState.Success(result.results.orEmpty())
        } catch (e: IOException) {
            mainUiState = MainUiState.Error
        }
    }


    init {
        getMovies()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication)
                val newsRepository = application.container.moviesRepository
                MainViewModel(newsRepository)
            }
        }
    }
}