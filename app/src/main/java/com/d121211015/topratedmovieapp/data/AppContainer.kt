package com.d121211015.topratedmovieapp.data

import com.d121211015.topratedmovieapp.data.repository.MovieRepository
import com.d121211015.topratedmovieapp.data.source.remote.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val moviesRepository: MovieRepository
}

class DefaultAppContainer: AppContainer {

    private val BASE_URL = "https://api.themoviedb.org"

//    private val AUTH_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5NGM1NjgxYWE3MTdjODlmZTdmMmE1ZTcyYTNhNWZmZCIsInN1YiI6IjYyZTRhOTJiZmM1ZjA2MDA1OWMyOGUwOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val moviesRepository: MovieRepository
        get() = MovieRepository(retrofitService)
}