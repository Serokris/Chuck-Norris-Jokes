package com.example.domain.usecases

import com.example.domain.common.Errors
import com.example.domain.models.Joke
import com.example.domain.repository.JokeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.example.domain.common.Result
import kotlinx.coroutines.flow.catch
import java.io.IOException

class FetchRandomJokeUseCase @Inject constructor(
    private val repository: JokeRepository
) {
    operator fun invoke(): Flow<Result<Joke>> = flow {
        emit(Result.Loading())
        val joke = repository.fetchRandomJoke()
        emit(Result.Success(joke))
    }.catch { exception ->
        when (exception) {
            is IOException -> {
                emit(Result.Error(Errors.COULDNT_REACH_SERVER))
            }
            else -> {
                emit(Result.Error(Errors.AN_UNEXPECTED_ERROR))
            }
        }
    }
}