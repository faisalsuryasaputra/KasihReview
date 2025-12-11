package com.example.kasihreview.Network

import com.example.animesearch.Util.Error
import com.example.animesearch.Util.NetworkError
import com.example.animesearch.Util.Result
import com.example.kasihreview.Model.ApiErrorResponse
import com.example.kasihreview.Model.BackendError
import com.example.kasihreview.Model.LoginRequest
import com.example.kasihreview.Model.LoginResponse
import com.example.kasihreview.Model.MovieForPost
import com.example.kasihreview.Model.MovieGoer
import com.example.kasihreview.Model.MovieGoerDTO
import com.example.kasihreview.Model.MovieSearchResult
import com.example.kasihreview.Model.MoviesDTO
import com.example.kasihreview.Model.Review
import com.example.kasihreview.Model.ReviewRequestDTO
import com.example.kasihreview.Model.ReviewResponse
import com.example.kasihreview.Model.VoteDTO
import com.example.kasihreview.Model.VoteRequestDTO
import com.example.kasihreview.Model.WatchlistDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import kotlinx.serialization.SerializationException
import java.nio.channels.UnresolvedAddressException

class KasihReviewClient(val client: HttpClient) {

    suspend fun postMovieGoer(user: MovieGoer): MovieGoer {
        return client.post("http://10.0.2.2:8080/api/moviegoers") {
            contentType(ContentType.Application.Json)
            setBody(user)
        }.body()
    }

    suspend fun postReview(
        movieId: Int,
        userId: Int,
        content: String,
        rating: Int,
        isSpoiler: Boolean
    ): Result<ReviewResponse, NetworkError> {

        val response = try {
            client.post("http://10.0.2.2:8080/api/reviews") {
                contentType(ContentType.Application.Json)
                setBody(ReviewRequestDTO(
                    movieId = movieId,
                    userId = userId,
                    content = content,
                    rating = rating,
                    isSpoiler = isSpoiler
                ))
            }
        }catch(e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch(e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when(response.status.value) {
            in 200..299 -> {
                val result = response.body<ReviewResponse>()
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

    suspend fun postMovie(movie: MoviesDTO): Result<MoviesDTO, NetworkError> {

        val response = try {
            client.post("http://10.0.2.2:8080/api/movies") {
                contentType(ContentType.Application.Json)
                setBody(movie)
            }
        }catch(e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch(e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when(response.status.value) {
            in 200..299 -> {
                val result = response.body<MoviesDTO>()
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

//    suspend fun loginAuth(username: String, password: String): Result<LoginResponse, NetworkError> {
//        val response = try {
//            client.post("http://10.0.2.2:8080/api/moviegoers/login") {
//                contentType(ContentType.Application.Json)
//                setBody(LoginRequest(username, password))
//            }
//        }catch(e: UnresolvedAddressException) {
//            return Result.Error(NetworkError.NO_INTERNET)
//        } catch(e: SerializationException) {
//            return Result.Error(NetworkError.SERIALIZATION)
//        }
//
//        return when(response.status.value) {
//            in 200..299 -> {
//                val result = response.body<LoginResponse>()
//                Result.Success(result)
//            }
//            401 -> Result.Error(NetworkError.UNAUTHORIZED)
//            409 -> Result.Error(NetworkError.CONFLICT)
//            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
//            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
//            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
//            else -> Result.Error(NetworkError.UNKNOWN)
//        }
//    }

    suspend fun loginAuth(
        username: String,
        password: String
    ): Result<LoginResponse, Error> {

        val response = try {
            client.post("http://10.0.2.2:8080/api/moviegoers/login") {
                contentType(ContentType.Application.Json)
                setBody(LoginRequest(username, password))
            }
        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when (response.status.value) {
            in 200..299 -> {
                val result = response.body<LoginResponse>()
                Result.Success(result)
            }

            else -> {
                // --- Coba baca JSON error dari backend ---
                val errorBody = try {
                    response.body<ApiErrorResponse>()
                } catch (e: Exception) {
                    null
                }

                // Kalau backend mengirim message → gunakan ke UI
                if (!errorBody?.message.isNullOrEmpty()) {
                    return Result.Error(
                        BackendError(
                            code = response.status.value,
                            message = errorBody!!.message!!
                        )
                    )
                }

                // --- fallback kalau error JSON tidak sesuai ---
                return when(response.status.value) {
                    401 -> Result.Error(NetworkError.UNAUTHORIZED)
                    409 -> Result.Error(NetworkError.CONFLICT)
                    408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
                    413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
                    in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
                    else -> Result.Error(NetworkError.UNKNOWN)
                }
            }
        }
    }


    suspend fun getReviewByMovieGoerId(id: Int): Result<List<ReviewResponse>, NetworkError> {
        val response = try {
            client.get("http://10.0.2.2:8080/api/reviews/user/$id")
        }catch(e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch(e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when(response.status.value) {
            in 200..299 -> {
                val result = response.body<List<ReviewResponse>>()
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

    suspend fun getReviewById(id: Int): Result<ReviewResponse, NetworkError> {
        val response = try {
            client.get("http://10.0.2.2:8080/api/reviews/$id")
        }catch(e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch(e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when(response.status.value) {
            in 200..299 -> {
                val result = response.body<ReviewResponse>()
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

    suspend fun getMovieGoerById(id: Int): Result<MovieGoerDTO, NetworkError> {
        val response = try {
            client.get("http://10.0.2.2:8080/api/moviegoers/$id")
        }catch(e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch(e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when(response.status.value) {
            in 200..299 -> {
                val result = response.body<MovieGoerDTO>()
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

    suspend fun getReviewByMovieId(id: Int): Result<List<ReviewResponse>, NetworkError> {
        val response = try {
            client.get("http://10.0.2.2:8080/api/movies/$id/reviews")
        }catch(e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch(e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when(response.status.value) {
            in 200..299 -> {
                val result = response.body<List<ReviewResponse>>()
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

    suspend fun postMovieToWatchList(userId: Int, movieId: Int): Result<WatchlistDTO, NetworkError> {
        val response = try {
            client.post("http://10.0.2.2:8080/api/watchlist/user/$userId/movie/$movieId") {
                contentType(ContentType.Application.Json)
            }
        }catch(e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch(e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when(response.status.value) {
            in 200..299 -> {
                val result = response.body<WatchlistDTO>()
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

    suspend fun postReviewVote(reviewVote: VoteRequestDTO, reviewId: Int): Result<String, NetworkError> {
        val response = try {
            client.post("http://10.0.2.2:8080/api/reviews/$reviewId/vote") {
                contentType(ContentType.Application.Json)
                setBody(reviewVote)
            }
        }catch(e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch(e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when(response.status.value) {
            in 200..299 -> {
                val result = response.body<String>()
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

    suspend fun getVoteByReviewId(reviewId: Int): Result<List<VoteDTO>, NetworkError> {
        val response = try {
            client.get("http://10.0.2.2:8080/api/reviews/$reviewId/votes") {
                contentType(ContentType.Application.Json)
            }
        }catch(e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch(e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when(response.status.value) {
            in 200..299 -> {
                val result = response.body<List<VoteDTO>>()
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

    suspend fun getVoteByMovieGoerId(movieId: Int, movieGoerId: Int): Result<List<VoteDTO>, NetworkError> {
        val response = try {
            client.get("http://10.0.2.2:8080/api/movies/$movieId/votes/moviegoer/$movieGoerId") {
                contentType(ContentType.Application.Json)
            }
        }catch(e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch(e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when(response.status.value) {
            in 200..299 -> {
                val result = response.body<List<VoteDTO>>()
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

    suspend fun patchUserVote(voteId: Int, reviewId: Int, voteType: String): Result<String, NetworkError> {
        val response = try {
            client.patch("http://10.0.2.2:8080/api/reviews/$reviewId/vote/$voteId") {
                contentType(ContentType.Application.Json)
                setBody(
                    mapOf(
                        "voteType" to voteType
                    )
                )
            }
        }catch(e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch(e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when(response.status.value) {
            in 200..299 -> {
                val result = response.body<String>()
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

    suspend fun deleteReviewVote(reviewId: Int, voteId: Int): Result<String, NetworkError> {
        val response = try {
            client.post("http://10.0.2.2:8080/api/reviews/$reviewId/vote/$voteId") {
                contentType(ContentType.Application.Json)
            }
        }catch(e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch(e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when(response.status.value) {
            in 200..299 -> {
                val result = response.body<String>()
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

    suspend fun getMovieById(id: Int): Result<Double, NetworkError> {
        val response = try {
            client.get("http://10.0.2.2:8080/api/movies/avgRating/$id") {
                contentType(ContentType.Application.Json)
            }
        }catch(e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch(e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when(response.status.value) {
            in 200..299 -> {
                val result = response.body<Double>()
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

    suspend fun updateUserProfile(user: MovieGoer): Result<MovieGoerDTO, NetworkError> {
        val response = try {
            client.patch("http://10.0.2.2:8080/api/moviegoers/${user.id}/profile") {
                contentType(ContentType.Application.Json)
                setBody(user)
            }
        }catch(e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch(e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when(response.status.value) {
            in 200..299 -> {
                val result = response.body<MovieGoerDTO>()
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

    suspend fun updateUserReviewContent(userId: Int, content: String): Result<ReviewRequestDTO, NetworkError> {
        val response = try {
            client.patch("http://10.0.2.2:8080/api/reviews/${userId}") {
                contentType(ContentType.Application.Json)
                setBody(
                    mapOf(
                        "content" to content
                    )
                )
            }
        }catch(e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch(e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when(response.status.value) {
            in 200..299 -> {
                val result = response.body<ReviewRequestDTO>()
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

    suspend fun updateUserReviewRating(userId: Int, rating: Int): Result<ReviewRequestDTO, NetworkError> {
        val response = try {
            client.patch("http://10.0.2.2:8080/api/reviews/${userId}") {
                contentType(ContentType.Application.Json)
                setBody(
                    mapOf(
                        "rating" to rating
                    )
                )
            }
        }catch(e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch(e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when(response.status.value) {
            in 200..299 -> {
                val result = response.body<ReviewRequestDTO>()
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

    suspend fun updateUserReviewSpoiler(userId: Int, isSpoiler: Boolean): Result<ReviewRequestDTO, NetworkError> {
        val response = try {
            client.patch("http://10.0.2.2:8080/api/reviews/${userId}") {
                contentType(ContentType.Application.Json)
                setBody(
                    mapOf(
                        "isSpoiler" to isSpoiler
                    )
                )
            }
        }catch(e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch(e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when(response.status.value) {
            in 200..299 -> {
                val result = response.body<ReviewRequestDTO>()
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

    suspend fun deleteMovieFromWatchList(userId: Int, movieId: Int): Result<WatchlistDTO, NetworkError> {
        val response = try {
            client.delete("http://10.0.2.2:8080/api/watchlist/user/$userId/movie/$movieId") {
                contentType(ContentType.Application.Json)
            }
        }catch(e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch(e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when(response.status.value) {
            in 200..299 -> {
                val result = response.body<WatchlistDTO>()
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

    suspend fun getWatchListByUserId(userId: Int): Result<WatchlistDTO, NetworkError> {
        val response = try {
            client.get("http://10.0.2.2:8080/api/watchlist/user/$userId") {
                contentType(ContentType.Application.Json)
            }
        }catch(e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch(e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when(response.status.value) {
            in 200..299 -> {
                val result = response.body<WatchlistDTO>()
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