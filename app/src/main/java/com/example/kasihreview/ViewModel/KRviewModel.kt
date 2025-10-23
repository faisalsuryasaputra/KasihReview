package com.example.kasihreview.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animesearch.Util.onSuccess
import com.example.kasihreview.Model.MovieSearchResult
import com.example.kasihreview.Network.TMDBclient
import com.example.kasihreview.Network.httpClient
import io.ktor.client.engine.cio.CIO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class KRviewModel: ViewModel() {
    val tmdbClient = TMDBclient(httpClient(CIO.create()))

    private val _popularMoviesUIState = MutableStateFlow(MovieSearchResult())
    val popularMoviesUIState = _popularMoviesUIState.asStateFlow()

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
}