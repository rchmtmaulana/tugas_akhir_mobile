package com.d121211015.topratedmovieapp.data.source.remote

import com.d121211015.topratedmovieapp.data.response.GetMovieResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers(
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzZWEwYWJiYzc5ZTE4ZjNjOWVmZmIyOWFhMGU1YjljYSIsInN1YiI6IjY1Nzk5Y2EyMjBlY2FmMDBlMzkyYjc4ZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.4b2mI9VU_zcAIj8rwLy1Fek-EgkOFRikyP6sbyLUeP4"
    )
    @GET("3/movie/top_rated")
    suspend fun getMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): GetMovieResponse
}