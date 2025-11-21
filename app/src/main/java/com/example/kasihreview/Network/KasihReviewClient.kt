package com.example.kasihreview.Network

import com.example.kasihreview.Model.MovieGoer
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class KasihReviewClient(val client: HttpClient) {

    suspend fun postMovieGoer(user: MovieGoer): MovieGoer {
        return client.post("http://10.0.2.2:8080/api/moviegoers") {
            contentType(ContentType.Application.Json)
            setBody(user)
        }.body()
    }
}