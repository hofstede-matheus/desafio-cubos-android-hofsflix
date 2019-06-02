package com.matheushofstede.hofsflix.network

import com.matheushofstede.hofsflix.TopRatedMovies
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class API {
    fun configuraRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
interface API_Interface {
    // requests
    @get:GET("movie/top_rated?page=1&language=en-US&api_key=6281c2ddd16cb4f569776050d4abe39f")
    val topRatedMovies: Call<TopRatedMovies>

    // outras requests aqui...
}