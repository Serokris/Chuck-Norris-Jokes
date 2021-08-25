package com.example.chucknorrisjokes.data.network

import com.example.chucknorrisjokes.data.network.response.JokeResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://api.chucknorris.io/"

interface JokeApiService {
    @GET("jokes/random")
    suspend fun fetchRandomJoke() : JokeResponse
}