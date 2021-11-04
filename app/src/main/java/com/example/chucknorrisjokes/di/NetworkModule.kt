package com.example.chucknorrisjokes.di

import com.example.chucknorrisjokes.utils.Constants.BASE_URL
import com.example.data.source.remote.JokeApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
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
    @Singleton
    @Provides
    fun provideJokesApiService(
        baseUrl: String,
        coroutineCallAdapterFactory: CoroutineCallAdapterFactory,
        gsonConverterFactory: GsonConverterFactory
    ): JokeApiService = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(coroutineCallAdapterFactory)
        .addConverterFactory(gsonConverterFactory)
        .build()
        .create(JokeApiService::class.java)

    @Singleton
    @Provides
    fun provideCoroutineCallAdapterFactory(): CoroutineCallAdapterFactory {
        return CoroutineCallAdapterFactory.invoke()
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideJokeApiServiceBaseUrl(): String = BASE_URL
}