package com.matheushofstede.hofsflix.Interfaces

import com.matheushofstede.hofsflix.TopRatedMovies
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    //urls
    @get:GET("movie/top_rated?page=1&language=en-US&api_key=6281c2ddd16cb4f569776050d4abe39f")
    val topRatedMovies: Call<TopRatedMovies>
}