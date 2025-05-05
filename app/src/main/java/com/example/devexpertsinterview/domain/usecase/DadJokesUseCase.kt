package com.example.devexpertsinterview.domain.usecase

import com.example.devexpertsinterview.domain.repository.DadJokesRepository
import javax.inject.Inject

class DadJokesUseCase @Inject constructor(
    private val dadJokesRepository: DadJokesRepository,
) {
    suspend fun searchJokes(term: String) = dadJokesRepository.searchJokes(term)
}