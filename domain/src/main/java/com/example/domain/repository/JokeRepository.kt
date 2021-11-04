package com.example.domain.repository

import com.example.domain.models.Joke

interface JokeRepository {
    suspend fun fetchRandomJoke(): Joke
}