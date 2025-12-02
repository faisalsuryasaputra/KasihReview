package com.example.kasihreview.Model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WatchlistDTO(
    @SerialName("watchlistId") val watchlistId: Int = -1,
    @SerialName("userId") val userId: Int = -1,
    @SerialName("username") val username: String = "",
    @SerialName("movies") val movies: List<MovieInWatchlistDTO> = emptyList()
)


@Serializable
data class MovieInWatchlistDTO(
    @SerialName("movieId") val movieId: Int = -1,
    @SerialName("movieTitle") val movieTitle: String = "",
    @SerialName("posterUrl") val posterUrl: String = "",
    @SerialName("releaseYear") val releaseYear: Int = 2000,
    @SerialName("avgRating") val avgRating: Float = 0f
)