package com.example.chucknorrisjokes.presentation.joke

import androidx.lifecycle.ViewModel
import com.example.domain.common.Result
import com.example.domain.models.Joke
import com.example.domain.usecases.FetchRandomJokeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class JokeListViewModel @Inject constructor(
    private val fetchRandomJokeUseCase: FetchRandomJokeUseCase
) : ViewModel() {
    suspend fun fetchRandomJoke(): Flow<Result<Joke>> = fetchRandomJokeUseCase.invoke()
}