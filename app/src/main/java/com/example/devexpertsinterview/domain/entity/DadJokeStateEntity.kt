package com.example.devexpertsinterview.domain.entity

sealed class DadJokeStateEntity {
    data class Success(val items: List<DadJokesResponseItemEntity>) : DadJokeStateEntity()
    data class Error(val message: String) : DadJokeStateEntity()
}