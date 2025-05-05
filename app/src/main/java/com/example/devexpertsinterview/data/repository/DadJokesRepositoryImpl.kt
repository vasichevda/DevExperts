package com.example.devexpertsinterview.data.repository

import android.util.Log
import com.example.devexpertsinterview.Constants
import com.example.devexpertsinterview.data.api.DadJokesApiService
import com.example.devexpertsinterview.data.entity.toEntity
import com.example.devexpertsinterview.domain.entity.DadJokeStateEntity
import com.example.devexpertsinterview.domain.repository.DadJokesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DadJokesRepositoryImpl @Inject constructor(
    private val apiService: DadJokesApiService,
) : DadJokesRepository {

    override suspend fun searchJokes(term: String): DadJokeStateEntity =
         try {
            val response = apiService.searchJokes(term)
            DadJokeStateEntity.Success(response.results.map { it.toEntity() })
        } catch (e: Exception) {
            Log.e(Constants.tag, "Failed to search jokes with term: $term")
            DadJokeStateEntity.Error(e.message.toString())
        }
}
