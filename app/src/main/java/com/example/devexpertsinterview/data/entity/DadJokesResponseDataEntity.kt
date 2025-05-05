package com.example.devexpertsinterview.data.entity

import com.example.devexpertsinterview.domain.entity.DadJokesResponseItemEntity

data class DadJokesResponseItemDataEntity(
    val id: String,
    val joke: String,
)

fun DadJokesResponseItemDataEntity.toEntity(): DadJokesResponseItemEntity =
    DadJokesResponseItemEntity(this.id, this.joke)
