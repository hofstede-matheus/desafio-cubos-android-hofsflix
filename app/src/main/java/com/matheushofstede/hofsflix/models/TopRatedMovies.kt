package com.matheushofstede.hofsflix

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// aqui só serve mesmo o results, que é uma array de filmes
class TopRatedMovies {

    @SerializedName("page")
    @Expose
    var page: Int? = null
    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null
    @SerializedName("results")
    @Expose
    var results = ArrayList<Movie>()


}
