package com.example.kasihreview.Model

import com.example.animesearch.Util.Error
import kotlinx.serialization.Serializable

@Serializable
data class ApiErrorResponse(
    val status: Int? = null,
    val error: String? = null,
    val message: String? = null,
    val timestamp: String? = null,
    val path: String? = null
)

data class BackendError(val code: Int, val message: String) : Error