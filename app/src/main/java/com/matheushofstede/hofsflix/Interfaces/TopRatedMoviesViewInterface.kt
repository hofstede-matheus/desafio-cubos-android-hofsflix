package com.matheushofstede.hofsflix.Interfaces

import com.matheushofstede.hofsflix.TopRatedMovies
import retrofit2.Response

interface TopRatedMoviesViewInterface {
    fun configuraRV()
    fun popularRV(response: Response<TopRatedMovies>)
}