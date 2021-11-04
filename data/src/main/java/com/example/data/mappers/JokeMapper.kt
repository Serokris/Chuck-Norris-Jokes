package com.example.data.mappers

import com.example.data.models.JokeResponse
import com.example.domain.models.Joke

fun JokeResponse.toJoke(): Joke = Joke(id, description)