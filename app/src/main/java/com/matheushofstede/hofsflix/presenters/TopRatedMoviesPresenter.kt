package com.matheushofstede.hofsflix.presenters

import android.util.Log
import com.matheushofstede.hofsflix.interfaces.TopRatedMoviesPresenterInterface
import com.matheushofstede.hofsflix.interfaces.TopRatedMoviesViewInterface
import com.matheushofstede.hofsflix.network.API
import com.matheushofstede.hofsflix.network.API_Interface
import com.matheushofstede.hofsflix.TopRatedMovies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class TopRatedMoviesPresenter(view: TopRatedMoviesViewInterface): TopRatedMoviesPresenterInterface {
    var view: TopRatedMoviesViewInterface = view

    // entrega uma instancia do Retrofit já configuradinho
    override fun configuraRetrofit(): Retrofit {
        return API().configuraRetrofit()
    }
    override fun enviaRequisicao(retrofit: Retrofit) {
        val service = retrofit.create(API_Interface::class.java)
        var call = service.topRatedMovies

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