package com.matheushofstede.hofsflix.Presenters

import android.util.Log
import com.matheushofstede.hofsflix.Interfaces.Api
import com.matheushofstede.hofsflix.Interfaces.TopRatedMoviesPresenterInterface
import com.matheushofstede.hofsflix.Interfaces.TopRatedMoviesViewInterface
import com.matheushofstede.hofsflix.TopRatedMovies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TopRatedMoviesPresenter(view: TopRatedMoviesViewInterface): TopRatedMoviesPresenterInterface {
    var view: TopRatedMoviesViewInterface = view

    // entrega uma instancia do Retrofit já configuradinho
    override fun configuraRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    override fun enviaRequisicao(retrofit: Retrofit) {
        val service = retrofit.create(Api::class.java)
        var call = service.results

        call.enqueue(object : Callback<TopRatedMovies> {
            override fun onResponse(call: Call<TopRatedMovies>, response: Response<TopRatedMovies>) {

                // caso dê tudo certo, pede pra view pouplar com os dados da resposta
                if (response.code() == 200) {
                    view.popularRV(response)
                }
            }
            override fun onFailure(call: Call<TopRatedMovies>, t: Throwable) {
                Log.e("ONFAILURE", t.message)
            }
        })

    }
}