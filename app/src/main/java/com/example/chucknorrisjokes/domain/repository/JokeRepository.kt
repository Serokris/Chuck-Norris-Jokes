package com.example.chucknorrisjokes.domain.repository

import com.example.chucknorrisjokes.domain.models.response.JokeResponse
import retrofit2.Response

interface JokeRepository {
    suspend fun fetchRandomJoke(): Response<JokeResponse>
}