package com.example.data.repository

import com.example.data.mappers.toJoke
import com.example.data.source.remote.JokeApiService
import com.example.domain.models.Joke
import com.example.domain.repository.JokeRepository
import javax.inject.Inject

class JokeRepositoryImpl @Inject constructor(
    private val jokeApiService: JokeApiService
) : JokeRepository {
    override suspend fun fetchRandomJoke(): Joke {
        return jokeApiService.fetchRandomJoke().toJoke()
    }
}