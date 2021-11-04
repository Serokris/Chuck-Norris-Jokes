package com.example.chucknorrisjokes.di

import com.example.data.repository.JokeRepositoryImpl
import com.example.data.source.remote.JokeApiService
import com.example.domain.repository.JokeRepository
import com.example.domain.usecases.FetchRandomJokeUseCase
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
    fun provideFetchRandomJokeUseCase(repository: JokeRepository): FetchRandomJokeUseCase {
        return FetchRandomJokeUseCase(repository)
    }
}