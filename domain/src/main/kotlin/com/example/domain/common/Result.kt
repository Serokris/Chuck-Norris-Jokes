package com.example.domain.common

sealed class Result<T>(val data: T? = null, val errorMessage: String? = null) {
    class Success<T>(data: T) : Result<T>(data)
    class Loading<T>(data: T? = null) : Result<T>(data)
    class Error<T>(errorMessage: String, data: T? = null) : Result<T>(data, errorMessage)
}