package com.example.domain.usecases

import com.example.domain.common.Errors
import com.example.domain.models.Joke
import com.example.domain.repository.JokeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.example.domain.common.Result

class FetchRandomJokeUseCase @Inject constructor(
    private val repository: JokeRepository
) {
    suspend operator fun invoke(): Flow<Result<Joke>> = flow {
        try {
            emit(Result.Loading())
            val joke = repository.fetchRandomJoke()
            emit(Result.Success(joke))
        } catch (e: Exception) {
            emit(Result.Error<Joke>(Errors.AN_UNEXPECTED_ERROR))
        }
    }
}