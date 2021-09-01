package com.example.chucknorrisjokes.data.repository

import com.example.chucknorrisjokes.data.source.remote.JokeApiService
import com.example.chucknorrisjokes.domain.repository.JokeRepository
import javax.inject.Inject

class JokeRepositoryImpl @Inject constructor(private val jokeApiService: JokeApiService): JokeRepository {
    override suspend fun fetchRandomJoke() = jokeApiService.fetchRandomJoke()
}