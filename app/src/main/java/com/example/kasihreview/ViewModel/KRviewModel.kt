package com.example.kasihreview.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animesearch.Util.NetworkError
import com.example.animesearch.Util.onError
import com.example.animesearch.Util.onSuccess
import com.example.kasihreview.Model.BackendError
import com.example.kasihreview.Model.MovieDetails
import com.example.kasihreview.Model.MovieGoer
import com.example.kasihreview.Model.MovieSearchResult
import com.example.kasihreview.Model.MoviesDTO
import com.example.kasihreview.Model.ReviewRequestDTO
import com.example.kasihreview.Model.ReviewResponse
import com.example.kasihreview.Model.VoteDTO
import com.example.kasihreview.Model.VoteList
import com.example.kasihreview.Model.VoteRequestDTO
import com.example.kasihreview.Model.WatchlistDTO
import com.example.kasihreview.Model.listOfReviewResponse
import com.example.kasihreview.Network.KasihReviewClient
import com.example.kasihreview.Network.TMDBclient
import com.example.kasihreview.Network.httpClient
import com.example.kasihreview.View.GenreDetails
import io.ktor.client.engine.cio.CIO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class KRviewModel: ViewModel() {
    val tmdbClient = TMDBclient(httpClient(CIO.create()))

    private val _userVoteList = MutableStateFlow(VoteList())
    val userVoteList = _userVoteList.asStateFlow()

    val kasihReviewClient = KasihReviewClient(httpClient(CIO.create()))

    private val _ulasanDetail = MutableStateFlow(ReviewResponse())
    val ulasanDetail = _ulasanDetail.asStateFlow()

    private val _currentSession = MutableStateFlow(MovieGoer())
    val currentSession = _currentSession.asStateFlow()

    private val _accountWatchList = MutableStateFlow(WatchlistDTO())
    val accountWatchList = _accountWatchList.asStateFlow()

    private val _accountReviews = MutableStateFlow(listOfReviewResponse())
    val accountReviews = _accountReviews.asStateFlow()

    private val _movieReviews = MutableStateFlow(listOfReviewResponse())
    val movieReviews = _movieReviews.asStateFlow()

    private val _moviesSearch = MutableStateFlow(MovieSearchResult())
    val moviesSearch = _moviesSearch.asStateFlow()

    private val _popularMoviesUIState = MutableStateFlow(MovieSearchResult())
    val popularMoviesUIState = _popularMoviesUIState.asStateFlow()

    private val _movieDetailsState = MutableStateFlow(MovieDetails())
    val movieDetailsState = _movieDetailsState.asStateFlow()

    private val _movieAvgRating = MutableStateFlow(0.0)
    val movieAvgRating = _movieAvgRating.asStateFlow()

    private val _accumulatedGenre = MutableStateFlow("")
    val accumulatedGenre = _accumulatedGenre.asStateFlow()

    fun addGenre(genreDetails: GenreDetails){
        _accumulatedGenre.update {
            it + genreDetails.id.toString() +"%2C"
        }
    }

    fun removeGenre(genreDetails: GenreDetails){
        _accumulatedGenre.update {
            it.replace(genreDetails.id.toString() +"%2C","")
        }
    }

     fun postMovie(movieForPost: MoviesDTO){
        viewModelScope.launch {
            kasihReviewClient.postMovie(movieForPost)
                .onSuccess {
                    println("sukses")
                }
                .onError {
                    print("gagal")
                }
        }

    }

    fun postReviewVote(reviewVote: VoteRequestDTO, reviewId: Int){
        viewModelScope.launch {
            kasihReviewClient.postReviewVote(reviewVote, reviewId)
                .onSuccess {
                    println("sukses")
                }
                .onError {
                    print("gagal")
                }
        }

    }

    fun deleteReviewVote(reviewVote: VoteRequestDTO, reviewId: Int, voteId: Int){
        viewModelScope.launch {
            _currentSession.value.id?.let {
                kasihReviewClient.deleteReviewVote(reviewId, voteId)
                    .onSuccess {
                        println("sukses")
                    }
                    .onError {
                        print("gagal")
                    }
            }
        }

    }

    fun getVotesByMovieGoerId(movieId: Int, movieGoerId: Int){
        viewModelScope.launch {
            _currentSession.value.id?.let {
                kasihReviewClient.getVoteByMovieGoerId(movieId, movieGoerId)
                    .onSuccess {apiCallResult ->
                        _userVoteList.update { uiState ->
                            uiState.copy(voteList = apiCallResult)
                        }
                    }
            }
        }

    }

    fun patchUserVote(voteId: Int, reviewId: Int, voteType: String){
        viewModelScope.launch {
            kasihReviewClient.patchUserVote(voteId, reviewId, voteType)
                .onSuccess {
                    println("sukses")
                }
        }

    }

    fun sortByYearAscend() {
        _moviesSearch.update { result ->
            result.copy(results = result.results.sortedBy { it.releaseYear })
        }
    }

    fun sortByYearDescend() {
        _moviesSearch.update { result ->
            result.copy(results = result.results.sortedByDescending { it.releaseYear })
        }
    }

    fun sortByNameAscend() {
        _moviesSearch.update { result ->
            result.copy(results = result.results.sortedBy { it.title })
        }
    }

    fun sortByNameDescend() {
        _moviesSearch.update { result ->
            result.copy(results = result.results.sortedByDescending { it.title })
        }
    }

    fun postReview(
        movieId: Int,
        userId: Int,
        content: String,
        rating: Int,
        isSpoiler: Boolean
    ){
        viewModelScope.launch {
            kasihReviewClient.postReview(
                movieId = movieId,
                userId = userId,
                content = content,
                rating = rating,
                isSpoiler = isSpoiler
            )
        }
    }

    fun getReviewByMovieGoerId(id: Int){
        viewModelScope.launch {
            kasihReviewClient.getReviewByMovieGoerId(id)
                .onSuccess {apiCallResult ->
                    _accountReviews.update { uiState ->
                        uiState.copy(allReviews = apiCallResult)
                    }
                }
        }
    }

    fun getMovieAvgRating(id: Int){
        viewModelScope.launch {
            kasihReviewClient.getMovieById(id)
                .onSuccess {apiCallResult ->
                    _movieAvgRating.update {
                        apiCallResult
                    }
                }
        }
    }

    fun getMovieGoerById(id: Int){
        viewModelScope.launch {
            kasihReviewClient.getMovieGoerById(id)
                .onSuccess {apiCallResult ->
                    _currentSession.update { uiState ->
                        uiState.copy(
                            id = apiCallResult.id,
                            username = apiCallResult.username,
                            bio = apiCallResult.bio,
                            avatar_url = apiCallResult.profilePicture
                        )
                    }
                }
        }
    }

    fun getReviewById(id: Int){
        viewModelScope.launch {
            kasihReviewClient.getReviewById(id)
                .onSuccess {apiCallResult ->
                    _ulasanDetail.update { uiState ->
                        uiState.copy(
                            reviewId = apiCallResult.reviewId,
                            reviewerName = apiCallResult.reviewerName,
                            movieId = apiCallResult.movieId,
                            movieTitle = apiCallResult.movieTitle,
                            content = apiCallResult.content,
                            rating = apiCallResult.rating,
                            isSpoiler = apiCallResult.isSpoiler,
                            createdAt = apiCallResult.createdAt,
                            upvotes = apiCallResult.upvotes,
                            downvotes = apiCallResult.downvotes
                        )
                    }
                }
        }
    }

    fun getReviewByMovieId(id: Int){
        viewModelScope.launch {
            kasihReviewClient.getReviewByMovieId(id)
                .onSuccess {apiCallResult ->
                    _movieReviews.update { uiState ->
                        uiState.copy(allReviews = apiCallResult)
                    }
                }
        }
    }

    fun postMovieGoer(movieGoer: MovieGoer) {
        viewModelScope.launch {
            kasihReviewClient.postMovieGoer(movieGoer)
        }
    }

    fun postMovieToWatchList(userId: Int, movieId: Int) {
        viewModelScope.launch {
            kasihReviewClient.postMovieToWatchList(userId, movieId)
                .onSuccess {
                    println(it)
                }
        }
    }

    fun updateUserProfile(user: MovieGoer) {
        viewModelScope.launch {
            kasihReviewClient.updateUserProfile(user)
                .onSuccess {
                    println(it)
                }
        }
    }

    fun updateUserReview(userId: Int, update: ReviewRequestDTO) {
        viewModelScope.launch {
            kasihReviewClient.updateUserReviewContent(userId, update.content)
                .onSuccess {
                    println(it)
                }

            kasihReviewClient.updateUserReviewRating(userId, update.rating)
                .onSuccess {
                    println(it)
                }

            kasihReviewClient.updateUserReviewSpoiler(userId, update.isSpoiler)
                .onSuccess {
                    println(it)
                }
        }
    }

    fun deleteMovieFromWatchList(userId: Int, movieId: Int) {
        viewModelScope.launch {
            kasihReviewClient.deleteMovieFromWatchList(userId, movieId)
                .onSuccess {
                    println(it)
                }
        }
    }

    fun getWatchListByUserId(userId: Int) {
        viewModelScope.launch {
            kasihReviewClient.getWatchListByUserId(userId)
                .onSuccess {apiCallResult ->
                    _accountWatchList.update { uiState ->
                        uiState.copy(
                            watchlistId = apiCallResult.watchlistId,
                            userId = apiCallResult.userId,
                            username = apiCallResult.username,
                            movies = apiCallResult.movies
                        )
                    }
                }
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

    fun loginAuthentication(username: String, password: String) {
        viewModelScope.launch {
            kasihReviewClient.loginAuth(username, password)
                .onSuccess { apiCallResult ->
                    _currentSession.update { uiState ->
                        uiState.copy(
                            id = apiCallResult.user.id,
                            username = apiCallResult.user.username,
                            bio = apiCallResult.user.bio,
                            avatar_url = apiCallResult.user.avatar_url
                        )
                    }
                }
                .onError { error ->
                    when(error) {
                        is BackendError -> println("Server says: ${error.message}")
                        is NetworkError -> println("Network error: $error")
                    }
                }
        }
    }
}