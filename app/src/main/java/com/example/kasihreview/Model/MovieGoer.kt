package com.example.kasihreview.Model

import kotlinx.serialization.Serializable


@Serializable
data class MovieGoer(
    //var user_id: Int,
    val username: String? = null,
    val bio: String? = null,
    val full_name: String? = null,
    val password_hash: String? = null,
    val salt: String? = null,
    val avatar_url: String? = null
)