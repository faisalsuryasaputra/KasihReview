package com.example.kasihreview.Model

import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class VoteRequestDTO(
    val voteType: String,
    val userId: Int
)

@Serializable
data class VoteDTO(
    val voteId: Int,
    val movieGoerId: Int,
    val voterName: String,
    val reviewId: Int,
    val voteType: String
)

data class VoteList(
    val voteList: List<VoteDTO> = emptyList()
)