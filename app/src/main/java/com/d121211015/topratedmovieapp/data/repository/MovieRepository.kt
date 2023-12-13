package com.d121211015.topratedmovieapp.data.repository

import com.d121211015.topratedmovieapp.data.response.GetMovieResponse
import com.d121211015.topratedmovieapp.data.source.remote.ApiService

class MovieRepository(private val apiService: ApiService) {

    suspend fun getMovies(): GetMovieResponse {
        return apiService.getMovies()
    }
}