package com.example.chucknorrisjokes.di

import com.example.chucknorrisjokes.utils.Constants.BASE_URL
import com.example.data.source.remote.JokeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideJokesApiService(
        baseUrl: String,
        gsonConverterFactory: GsonConverterFactory
    ): JokeApiService = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(gsonConverterFactory)
        .build()
        .create(JokeApiService::class.java)

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideJokeApiServiceBaseUrl(): String = BASE_URL
}