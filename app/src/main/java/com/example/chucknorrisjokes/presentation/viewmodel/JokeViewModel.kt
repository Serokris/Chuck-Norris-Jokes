package com.example.chucknorrisjokes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.chucknorrisjokes.domain.usecases.JokeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JokeViewModel @Inject constructor(private val jokeUseCase: JokeUseCase) : ViewModel() {
    suspend fun fetchRandomJoke() = jokeUseCase.fetchRandomJoke()
}