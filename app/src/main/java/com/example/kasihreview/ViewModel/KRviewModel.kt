package com.example.kasihreview.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animesearch.Util.onSuccess
import com.example.kasihreview.Model.MovieDetails
import com.example.kasihreview.Model.MovieGoer
import com.example.kasihreview.Model.MovieSearchResult
import com.example.kasihreview.Model.genre
import com.example.kasihreview.Network.KasihReviewClient
import com.example.kasihreview.Network.TMDBclient
import com.example.kasihreview.Network.httpClient
import com.example.kasihreview.Security.Hash
import com.example.kasihreview.View.GenreDetails
import io.ktor.client.engine.cio.CIO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class KRviewModel: ViewModel() {
    val tmdbClient = TMDBclient(httpClient(CIO.create()))
    val hash = Hash()

    val kasihReviewClient = KasihReviewClient(httpClient(CIO.create()))


    private val _moviesSearch = MutableStateFlow(MovieSearchResult())
    val moviesSearch = _moviesSearch.asStateFlow()

    private val _popularMoviesUIState = MutableStateFlow(MovieSearchResult())
    val popularMoviesUIState = _popularMoviesUIState.asStateFlow()

    private val _movieDetailsState = MutableStateFlow(MovieDetails())
    val movieDetailsState = _movieDetailsState.asStateFlow()

    private val _accumulatedGenre = MutableStateFlow("")
    val accumulatedGenre = _accumulatedGenre.asStateFlow()

    fun addGenre(genreDetails: GenreDetails){
        _accumulatedGenre.update {
            it + genreDetails.id.toString() +"%7C"
        }
    }

    fun removeGenre(genreDetails: GenreDetails){
        _accumulatedGenre.update {
            it.replace(genreDetails.id.toString() +"%7C","")
        }
    }

    fun postMovieGoer(movieGoer: MovieGoer) {
        viewModelScope.launch {
            kasihReviewClient.postMovieGoer(movieGoer)
        }
    }

    fun getPopularMovies(){
        viewModelScope.launch {
            tmdbClient.getPopularMovies()
                .onSuccess {apiCallResult ->
                    _popularMoviesUIState.update { uiState ->
                        uiState.copy(results = apiCallResult.results)
                    }
                }
        }
    }

    fun getMoviesByName(name: String){
        viewModelScope.launch {
            tmdbClient.getMoviesByName(name)
                .onSuccess { apiCallResult ->
                    _moviesSearch.update { uiState ->
                        uiState.copy(results = apiCallResult.results)
                    }
                }
        }
    }

    fun getMovieDetailsById(id: Int){
        viewModelScope.launch {
            tmdbClient.getMovieDetailsById(id)
                .onSuccess { apiCallResult ->
                    _movieDetailsState.update { uiState ->
                        uiState.copy(
                            movie_Id = apiCallResult.movie_Id,
                            title = apiCallResult.title,
                            releaseYear = apiCallResult.releaseYear,
                            genres = apiCallResult.genres,
                            description = apiCallResult.description,
                            poster_Url = apiCallResult.poster_Url
                        )
                    }
                }
        }
    }

    fun getMoviesByGenres(genres: String){
        viewModelScope.launch {
            tmdbClient.getMoviesByGenres(genres)
                .onSuccess { apiCallResult ->
                    _moviesSearch.update { uiState ->
                        uiState.copy(results = apiCallResult.results)
                    }
                }
        }
    }

//    fun loginAuthentication(username: String, password: String): Boolean{
//        val dummy = createDummyMovieGoers()
//        for (user in dummy) {
//            if (username == user.username) {
//                return hash.verifyPassword(password, user.password_hash, hash.base64ToSalt(user.salt))
//            }
//        }
//        return false
//    }
}