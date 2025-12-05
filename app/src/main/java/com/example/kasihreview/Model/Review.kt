package com.example.kasihreview.Model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class Review(
    val review_id: Int? = null,
    val rating: Float? = 0f,
    val review_text: String? = "Empty",
    val is_spoiler: Boolean? = false,
    val created_at: String? = null
)

@Serializable
data class ReviewRequestDTO(
    val movieId: Int = -1,
    val userId: Int = -1,
    val content: String = "",
    val rating: Int = 0,
    val isSpoiler: Boolean = false
)

@Serializable
data class ReviewResponse(
    @SerialName("reviewId") val reviewId: Int = -1,
    @SerialName("reviewerName") val reviewerName: String = "",
    @SerialName("movieId") val movieId: Int = -1,
    @SerialName("movieTitle") val movieTitle: String? = "",
    @SerialName("content") val content: String = "",
    @SerialName("rating") val rating: Int = -1,
    @SerialName("spoiler") val isSpoiler: Boolean = false,
    @SerialName("createdAt") val createdAt: String = "",
    @SerialName("upvotes") val upvotes: Int = -1,
    @SerialName("downvotes") val downvotes: Int = -1
)

@Serializable
data class listOfReviewResponse(
    val allReviews: List<ReviewResponse> = emptyList()
)