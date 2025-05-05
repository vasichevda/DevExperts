package com.example.devexpertsinterview.data.api

import com.example.devexpertsinterview.data.entity.DadJokesResponseItemDataEntity
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface DadJokesApiService {
    @GET("/search")
    suspend fun searchJokes(
        @Query("term") term: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20,
        @Header("Accept") acceptHeader: String = "application/json",
    ): DadJokesSearchResponse
}

data class DadJokesSearchResponse(
    val currentPage: Int,
    val limit: Int,
    val nextPage: Int,
    val previousPage: Int,
    val results: List<DadJokesResponseItemDataEntity>,
    val searchTerm: String,
    val status: Int,
    val totalJokes: Int,
    val totalPages: Int,
)