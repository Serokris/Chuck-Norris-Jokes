package com.example.chucknorrisjokes.repository

import com.example.chucknorrisjokes.data.network.JokeApiService
import com.example.chucknorrisjokes.data.network.response.JokeResponse
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class JokeRepository @Inject constructor(private val jokeApiService: JokeApiService) {
    suspend fun fetchRandomJoke() = jokeApiService.fetchRandomJoke()
}