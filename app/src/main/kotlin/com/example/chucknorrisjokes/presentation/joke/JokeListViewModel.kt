package com.example.chucknorrisjokes.presentation.joke

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.Result
import com.example.domain.models.Joke
import com.example.domain.usecases.FetchRandomJokeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class JokeListViewModel @Inject constructor(
    private val fetchRandomJokeUseCase: FetchRandomJokeUseCase
) : ViewModel() {

    private val _dataLoading = MutableLiveData(false)
    val dataLoading: LiveData<Boolean> get() = _dataLoading

    private val _joke = MutableLiveData<Joke>()
    val joke: LiveData<Joke> get() = _joke

    private val _errorMessage = MutableLiveData("")
    val errorMessage: LiveData<String> get() = _errorMessage

    fun fetchRandomJoke() {
        fetchRandomJokeUseCase.invoke().onEach { result ->
            when (result) {
                is Result.Loading -> {
                    _dataLoading.postValue(true)
                }
                is Result.Success -> {
                    _dataLoading.postValue(false)
                    _joke.postValue(result.data!!)
                }
                is Result.Error -> {
                    _dataLoading.postValue(false)
                    _errorMessage.postValue(result.errorMessage)
                }
            }
        }.launchIn(viewModelScope)
    }
}