package com.example.chucknorrisjokes.viewmodel

import androidx.lifecycle.ViewModel
import com.example.chucknorrisjokes.repository.JokeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JokeViewModel @Inject constructor(private val repository: JokeRepository) : ViewModel() {
    suspend fun fetchRandomJoke() = repository.fetchRandomJoke()
}