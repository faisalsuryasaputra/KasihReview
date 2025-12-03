package com.example.kasihreview.Model

import kotlinx.serialization.Serializable


@Serializable
data class MovieGoer(
    val id: Int? = -1,
    val username: String? = "John Doe",
    val bio: String? = "Empty Bio",
    val full_name: String? = null,
    val password_hash: String? = null,
    val salt: String? = null,
    val avatar_url: String? = null
)

@Serializable
data class MovieGoerDTO(
    val id: Int,
    val username: String,
    val email: String?,
    val profilePicture: String?,
    val bio: String?
)