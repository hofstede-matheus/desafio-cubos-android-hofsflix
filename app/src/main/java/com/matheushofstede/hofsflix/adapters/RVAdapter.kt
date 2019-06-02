package com.matheushofstede.hofsflix.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.matheushofstede.hofsflix.Movie
import com.matheushofstede.hofsflix.R
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.matheushofstede.hofsflix.views.MovieActivity


class RVAdapter(private val list: ArrayList<Movie>, val context: Context): RecyclerView.Adapter<RVAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.movie_card, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = list[position]
        holder.bind(movie, context)
    }

    override fun getItemCount(): Int = list.size

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        // antes coloquei no construtor, mas o Android Studio sugeriu declarar e inicializar tudo junto e não deu problema, blz. Pelo menos o código fica menor
        private var title: TextView = itemView.findViewById(R.id.title)
        private var poster: ImageView = itemView.findViewById(R.id.movie_poster)
        private lateinit var movie: Movie

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val context = itemView.context
            val movieActivityIntent = Intent(context, MovieActivity::class.java)
            // poderia mandar as strings logo, mas preferi mandar o objeto todo
            movieActivityIntent.putExtra("MOVIE", movie)
            context.startActivity(movieActivityIntent)
            Log.d("RecyclerView", "CLICK!")
        }

        fun bind(movie: Movie, context: Context) {
            this.movie = movie
            title?.text = movie.title

            Glide
                .with(context)
                .load("https://image.tmdb.org/t/p/w342" + movie.posterPath) //width 342 é um tamanho bom pra o poster
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerInside() // pra tirar o problema do poster indo além do card
                .transition(withCrossFade())
                .into(poster)
        }
    }
}