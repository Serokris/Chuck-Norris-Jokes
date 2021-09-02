package com.example.chucknorrisjokes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.chucknorrisjokes.domain.models.response.JokeResponse
import com.example.chucknorrisjokes.domain.usecases.JokeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class JokeViewModel @Inject constructor(private val jokeUseCase: JokeUseCase) : ViewModel() {
    suspend fun fetchRandomJoke(): Response<JokeResponse> {
        return withContext(Dispatchers.IO) { jokeUseCase.fetchRandomJoke() }
    }
}