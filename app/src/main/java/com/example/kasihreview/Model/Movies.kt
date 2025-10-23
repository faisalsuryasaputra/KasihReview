package com.example.kasihreview.Model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieSearchResult(val results: List<Movies> = emptyList())

@Serializable
data class Movies(
    @SerialName("id")
    var movie_Id: Int,
    @SerialName("original_title")
    var title: String,
    @SerialName("release_date")
    var releaseYear: String,
    @SerialName("genre_ids")
    var genres: List<Int>,
    @SerialName("overview")
    var description: String,
    @SerialName("poster_path")
    var poster_Url: String
)

@Serializable
data class genre(var name: String)

