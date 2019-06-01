package com.matheushofstede.hofsflix.Views

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.matheushofstede.hofsflix.R
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.LinearLayoutManager
import com.matheushofstede.hofsflix.Adapters.RVAdapter
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.widget.Toast
import com.matheushofstede.hofsflix.Interfaces.Api
import com.matheushofstede.hofsflix.Interfaces.TopRatedMoviesPresenterInterface
import com.matheushofstede.hofsflix.Interfaces.TopRatedMoviesViewInterface
import com.matheushofstede.hofsflix.Movie
import com.matheushofstede.hofsflix.Presenters.TopRatedMoviesPresenter
import com.matheushofstede.hofsflix.TopRatedMovies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class MainActivity : AppCompatActivity(), TopRatedMoviesViewInterface {

    var adapter: RVAdapter? = null
    var linearLayoutManager: LinearLayoutManager? = null

    lateinit var rv: RecyclerView

    lateinit var presenter: TopRatedMoviesPresenterInterface


    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                //textMessage.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                //textMessage.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(com.matheushofstede.hofsflix.R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        presenter = TopRatedMoviesPresenter(this)

        configuraRV()
        presenter.enviaRequisicao(presenter.configuraRetrofit())

    }
    // Configura o RecycleView
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


























