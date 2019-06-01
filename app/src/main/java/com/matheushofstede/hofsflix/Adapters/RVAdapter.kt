package com.matheushofstede.hofsflix.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.matheushofstede.hofsflix.Movie
import com.matheushofstede.hofsflix.R
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.matheushofstede.hofsflix.TopRatedMovies


class RVAdapter(private val list: ArrayList<Movie>, val context: Context): RecyclerView.Adapter<RVAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = list.get(position)
        holder.bind(movie, context)
    }

    override fun getItemCount(): Int = list.size

    class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.movie_card, parent, false)) {
        private var title: TextView
        private var poster: ImageView


        init {
            title = itemView.findViewById(R.id.title)
            poster = itemView.findViewById(R.id.movie_poster)
        }

        fun bind(movie: Movie, context: Context) {
            title?.text = movie.title


            Glide
                .with(context)
                .load("https://image.tmdb.org/t/p/w200" + movie.posterPath)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerInside()
                .transition(withCrossFade())
                .into(poster)



        }

    }

}