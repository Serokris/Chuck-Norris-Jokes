package com.example.chucknorrisjokes.domain.repository

import com.example.chucknorrisjokes.domain.models.response.JokeResponse

interface JokeRepository {
    suspend fun fetchRandomJoke(): JokeResponse
}