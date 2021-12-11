package com.example.data.source.remote

import com.example.data.models.JokeResponse
import retrofit2.http.GET

interface JokeApiService {
    @GET("jokes/random")
    suspend fun fetchRandomJoke() : JokeResponse
}