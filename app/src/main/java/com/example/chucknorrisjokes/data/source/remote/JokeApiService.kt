package com.example.chucknorrisjokes.data.source.remote

import com.example.chucknorrisjokes.domain.models.response.JokeResponse
import retrofit2.http.GET

interface JokeApiService {
    @GET("jokes/random")
    suspend fun fetchRandomJoke() : JokeResponse
}