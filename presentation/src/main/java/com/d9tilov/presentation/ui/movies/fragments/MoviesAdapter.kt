package com.d9tilov.presentation.ui.movies.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.d9tilov.presentation.R
import com.d9tilov.presentation.entities.Movie
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.movie_item.view.*

class MoviesAdapter constructor(
    private val onMovieSelected:
        (Movie, View) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.MovieCellViewHolder>() {

    private val movies: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCellViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.movie_item,
            parent,
            false
        )
        return MovieCellViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieCellViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie, onMovieSelected)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun addMovies(movies: List<Movie>) {
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    inner class MovieCellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        LayoutContainer {
        override val containerView: View?
            get() = itemView

        fun bind(movie: Movie, listener: (Movie, View) -> Unit) = with(itemView) {
            title.text = movie.originalTitle
            movie.posterPath?.let {
                image.load(it)
            }
            setOnClickListener { listener(movie, itemView) }
        }
    }
}
