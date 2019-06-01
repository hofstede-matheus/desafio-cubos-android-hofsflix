package com.matheushofstede.hofsflix.Interfaces

import retrofit2.Retrofit

interface TopRatedMoviesPresenterInterface {
    fun configuraRetrofit(): Retrofit
    fun enviaRequisicao(retrofit: Retrofit)
}