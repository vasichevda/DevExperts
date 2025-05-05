package com.example.devexpertsinterview.domain.repository

import com.example.devexpertsinterview.domain.entity.DadJokeStateEntity

interface DadJokesRepository {
    suspend fun searchJokes(term: String): DadJokeStateEntity
}