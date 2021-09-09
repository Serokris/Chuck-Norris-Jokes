package com.example.chucknorrisjokes.domain.usecases

import com.example.chucknorrisjokes.domain.models.response.JokeResponse
import com.example.chucknorrisjokes.domain.repository.JokeRepository
import retrofit2.Response
import javax.inject.Inject

class FetchRandomJokeUseCase @Inject constructor(private val repository: JokeRepository) {
    suspend fun execute(): Response<JokeResponse> {
        return repository.fetchRandomJoke()
    }
}