package com.example.kasihreview.Model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieSearchResult(val results: List<Movies> = emptyList())

@Serializable
data class Movies(
    @SerialName("id")
    var movie_Id: Int?,
    @SerialName("original_title")
    var title: String?,
    @SerialName("release_date")
    var releaseYear: String?,
    @SerialName("genre_ids")
    var genres: List<Int>?,
    @SerialName("overview")
    var description: String?,
    @SerialName("poster_path")
    var poster_Url: String?
)

@Serializable
data class MovieForPost(
    val movieId: Int,
    val title: String = "Placeholder",
    val releaseYear: Int = 2000,
    val genre: List<String> = emptyList(),
    val description: String = "Description",
    val posterUrl: String = "Poster",
    val avgRating: Float = 0f
)

@Serializable
data class MoviesDTO(
    val movieId: Int,
    val title: String,
    val genre: List<String>,
    val releaseYear: Int,
    val description: String,
    val rating: Double,
    val posterUrl: String
)

@Serializable
data class MovieDetails(
    @SerialName("id")
    var movie_Id: Int? = 0,
    @SerialName("original_title")
    var title: String? = "",
    @SerialName("release_date")
    var releaseYear: String? = "",
    @SerialName("genres")
    var genres: List<genre>? = emptyList(),
    @SerialName("overview")
    var description: String? = "",
    @SerialName("poster_path")
    var poster_Url: String? = ""
)

@Serializable
data class genre(var name: String)

