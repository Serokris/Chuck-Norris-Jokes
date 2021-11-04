package com.example.chucknorrisjokes.utils

import android.view.View

fun View.show() = run { visibility = View.VISIBLE }

fun View.hide() = run { visibility = View.INVISIBLE }