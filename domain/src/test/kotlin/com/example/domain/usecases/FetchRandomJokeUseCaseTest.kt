package com.example.domain.usecases

import com.example.domain.common.Errors
import com.example.domain.models.Joke
import com.example.domain.repository.JokeRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito
import com.example.domain.common.Result
import kotlinx.coroutines.flow.last
import org.mockito.kotlin.mock
import java.io.IOException
import java.lang.Exception

class FetchRandomJokeUseCaseTest {

    private val fakeJokeRepository = mock<JokeRepository>()
    private val fetchRandomJokeUseCase = FetchRandomJokeUseCase(fakeJokeRepository)
    private val joke = Joke("id", "Name")

    @Test
    fun `The first value emitted should be is 'Loading'`() = runBlocking {
        Mockito.`when`(fakeJokeRepository.fetchRandomJoke()).thenReturn(joke)
        val result = fetchRandomJokeUseCase().first()
        assert(result is Result.Loading)
    }

    @Test
    fun `The last value emitted should be is 'Success'`() = runBlocking {
        Mockito.`when`(fakeJokeRepository.fetchRandomJoke()).thenReturn(joke)
        val result = fetchRandomJokeUseCase().last()
        assert(result is Result.Success && result.data == joke)
    }

    @Test
    fun `Expected catching IOException`() = runBlocking {
        Mockito.`when`(fakeJokeRepository.fetchRandomJoke()).then {
            throw IOException()
        }
        val result = fetchRandomJokeUseCase().last()
        assert(result is Result.Error && result.errorMessage == Errors.COULDNT_REACH_SERVER)
    }

    @Test
    fun `Expected catching Exception`() = runBlocking {
        Mockito.`when`(fakeJokeRepository.fetchRandomJoke()).then {
            throw Exception()
        }
        val result = fetchRandomJokeUseCase().last()
        assert(result is Result.Error && result.errorMessage == Errors.AN_UNEXPECTED_ERROR)
    }
}