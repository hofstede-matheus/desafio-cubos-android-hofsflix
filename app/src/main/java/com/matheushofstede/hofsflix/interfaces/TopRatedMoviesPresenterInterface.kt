package com.matheushofstede.hofsflix.interfaces

import retrofit2.Retrofit

interface TopRatedMoviesPresenterInterface {
    fun configuraRetrofit(): Retrofit
    fun enviaRequisicao(retrofit: Retrofit)
}