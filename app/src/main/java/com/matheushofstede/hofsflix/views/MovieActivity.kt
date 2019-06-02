package com.matheushofstede.hofsflix.views

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.matheushofstede.hofsflix.interfaces.MovieViewInterface
import com.matheushofstede.hofsflix.Movie
import kotlinx.android.synthetic.main.activity_movie.*
import kotlinx.android.synthetic.main.content_movie.*
import java.text.SimpleDateFormat
import java.util.*


class MovieActivity : AppCompatActivity(), MovieViewInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.matheushofstede.hofsflix.R.layout.activity_movie)
        setSupportActionBar(toolbar)

        // ativa e coloca pra o back button voltar, antes não tava aparecendo
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Também te amamos <3", Snackbar.LENGTH_SHORT).show()
        }

        // pega o objeto serializado que o Adapter da RV enviou
        // eu podia fazer uma request com o Refrofit novamente? Podia. Mas ai seria pedir uma coisa que eu já tenho em mãos, e ainda é trabalho com rede
        val movie: Movie = intent.extras.getSerializable("MOVIE") as Movie

        popularActivity(movie)
    }

    override fun popularActivity(movie: Movie) {
        // não achei o textview que muda o título na toolbar, mas descobri que ele é o titulo da activity, então vou usar
        title = movie.title

        tv_overview.text = movie.overview
        tv_release_date.text = formataData(movie.releaseDate)
        Glide
            .with(applicationContext)
            .load("https://image.tmdb.org/t/p/w780" + movie.backdropPath) //com width 780 é a resolução mais baixa disponível com uma qualidade decente
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerInside() // pra tirar o problema do poster indo além do card
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(backdrop)


    }
    // Conveste o formato de data estranho cuspido pela api pra nossa lingua
    fun formataData(data: String?): String{
        val date_n = SimpleDateFormat("yy-MM-dd")
        val output = SimpleDateFormat("dd MMMM yyyy", Locale("pt", "BR"))
        return output.format(date_n.parse(data))
    }
}
