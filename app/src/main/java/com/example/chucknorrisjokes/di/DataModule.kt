package com.example.chucknorrisjokes.di

import com.example.chucknorrisjokes.data.repository.JokeRepositoryImpl
import com.example.chucknorrisjokes.data.source.remote.JokeApiService
import com.example.chucknorrisjokes.domain.repository.JokeRepository
import com.example.chucknorrisjokes.domain.usecases.JokeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Singleton
    @Provides
    fun provideJokeRepository(apiService: JokeApiService): JokeRepository {
        return JokeRepositoryImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideJokeUseCase(repository: JokeRepository): JokeUseCase {
        return JokeUseCase(repository)
    }
}