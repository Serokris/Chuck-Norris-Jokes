package com.example.chucknorrisjokes.data.network.response

import com.google.gson.annotations.SerializedName

data class JokeResponse(
    val id: String,
    @SerializedName("value") val description: String
)