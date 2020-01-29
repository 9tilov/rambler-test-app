package com.d9tilov.presentation.ui.movies.fragments

import com.d9tilov.presentation.entities.Movie

data class PopularMoviesViewState(
    var showLoading: Boolean = true,
    var movies: List<Movie>? = null
)
