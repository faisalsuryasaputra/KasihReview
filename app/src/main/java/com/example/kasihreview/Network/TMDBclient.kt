package com.example.kasihreview.Network

import com.example.animesearch.Util.NetworkError
import com.example.animesearch.Util.Result
import com.example.kasihreview.Model.MovieSearchResult
import com.example.kasihreview.Model.Movies
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.http.HttpHeaders
import kotlinx.serialization.SerializationException
import java.nio.channels.UnresolvedAddressException

class TMDBclient(val client: HttpClient) {
    suspend fun getMovieById(): Result<Movies, NetworkError> {
        val response = try {
            client.get(urlString = "https://api.themoviedb.org/3/movie/550?language=en-US") {
                header(HttpHeaders.Accept, "application/json")
                header(HttpHeaders.Authorization, "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkODc5NmM1NTRlYWQyYTk2OWQwYTY4NTg5ODZiYWMxYSIsIm5iZiI6MTc2MTE0NzEzNi41ODgsInN1YiI6IjY4ZjhmOTAwM2NkYWRiYmI3MDQ4MzBiOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.m_ok0d03kfeaZ_5_wHKBAgBxP90sWgqzd1vaidOdycE")
            }
        }catch(e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch(e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when(response.status.value) {
            in 200..299 -> {
                val result = response.body<Movies>()
                Result.Success(result)
            }
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }

    suspend fun getPopularMovies(): Result<MovieSearchResult, NetworkError> {
        val response = try {
            client.get(urlString = "https://api.themoviedb.org/3/movie/popular?language=en-US&page=1") {
                header(HttpHeaders.Accept, "application/json")
                header(HttpHeaders.Authorization, "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkODc5NmM1NTRlYWQyYTk2OWQwYTY4NTg5ODZiYWMxYSIsIm5iZiI6MTc2MTE0NzEzNi41ODgsInN1YiI6IjY4ZjhmOTAwM2NkYWRiYmI3MDQ4MzBiOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.m_ok0d03kfeaZ_5_wHKBAgBxP90sWgqzd1vaidOdycE")
            }
        }catch(e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch(e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when(response.status.value) {
            in 200..299 -> {
                val result = response.body<MovieSearchResult>()
                Result.Success(result)
            }
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }
}