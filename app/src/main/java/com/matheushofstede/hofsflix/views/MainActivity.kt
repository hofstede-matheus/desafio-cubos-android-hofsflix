package com.matheushofstede.hofsflix.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.matheushofstede.hofsflix.adapters.RVAdapter
import com.matheushofstede.hofsflix.interfaces.TopRatedMoviesPresenterInterface
import com.matheushofstede.hofsflix.interfaces.TopRatedMoviesViewInterface
import com.matheushofstede.hofsflix.presenters.TopRatedMoviesPresenter
import com.matheushofstede.hofsflix.R
import com.matheushofstede.hofsflix.TopRatedMovies
import retrofit2.Response


class MainActivity : AppCompatActivity(), TopRatedMoviesViewInterface {

    var adapter: RVAdapter? = null
    var linearLayoutManager: LinearLayoutManager? = null

    lateinit var rv: RecyclerView

    lateinit var presenter: TopRatedMoviesPresenterInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = TopRatedMoviesPresenter(this)

        configuraRV()
        presenter.enviaRequisicao(presenter.configuraRetrofit())

    }
    // Configura o RecycleView 2 por linha
    override fun configuraRV() {
        rv = findViewById(R.id.rv)
        linearLayoutManager = GridLayoutManager(this, 2)
        rv.layoutManager = linearLayoutManager
        rv.itemAnimator = DefaultItemAnimator()
    }
    // Seta o Adapter com os dados da resposta já
    override fun popularRV(response: Response<TopRatedMovies>) {
        var movieResponse = response.body()!!.results
        adapter = RVAdapter(movieResponse, this@MainActivity)
        rv.setAdapter(adapter)
    }

}


























