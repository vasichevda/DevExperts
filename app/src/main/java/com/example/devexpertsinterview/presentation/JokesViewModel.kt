package com.example.devexpertsinterview.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devexpertsinterview.Constants
import com.example.devexpertsinterview.domain.entity.DadJokeStateEntity
import com.example.devexpertsinterview.domain.usecase.DadJokesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class JokesViewModel @Inject constructor(
    private val dadJokesUseCase: DadJokesUseCase,
) : ViewModel() {

    private val _jokesState = MutableStateFlow<DadJokesViewModelState>(DadJokesViewModelState.Init)
    val jokesState: StateFlow<DadJokesViewModelState> = _jokesState

    private val searchChannel = Channel<String>(capacity = Channel.CONFLATED)

    init {
        viewModelScope.launch {
            searchChannel.consumeEach { query ->
                performSearch(query)
            }
        }
    }

    fun onEnterSearch(query: String) {
        searchChannel.trySend(query)
    }

    private suspend fun performSearch(searchLine: String) {
        try {
            _jokesState.value = DadJokesViewModelState.Loading
            val result = dadJokesUseCase.searchJokes(searchLine)
            _jokesState.value =
                when (result) {
                    is DadJokeStateEntity.Error ->
                        DadJokesViewModelState.Error(result.message)

                    is DadJokeStateEntity.Success ->
                        if (result.items.isNotEmpty()) {
                            DadJokesViewModelState.Success(result.items.map { it.joke })
                        } else {
                            DadJokesViewModelState.Empty
                        }
                }
        } catch (e: Exception) {
            Log.e(Constants.tag, e.message ?: "Unknown error")
        }
    }
}

sealed class DadJokesViewModelState {
    data object Init : DadJokesViewModelState()
    data object Empty : DadJokesViewModelState()
    data object Loading : DadJokesViewModelState()
    data class Success(val jokes: List<String>) : DadJokesViewModelState()
    data class Error(val message: String) : DadJokesViewModelState()
}
