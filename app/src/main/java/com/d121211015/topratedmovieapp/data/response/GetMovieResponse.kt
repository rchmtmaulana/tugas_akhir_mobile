package com.d121211015.topratedmovieapp.data.response

import com.d121211015.topratedmovieapp.data.models.TopMovie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetMovieResponse(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<TopMovie>,
    @SerialName("total_pages")
    val total_pages: Int,
    @SerialName("total_results")
    val total_results: Int
)