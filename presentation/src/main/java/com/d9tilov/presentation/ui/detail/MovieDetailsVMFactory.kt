package com.d9tilov.presentation.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.d9tilov.domain.Mapper
import com.d9tilov.domain.entities.MovieEntity
import com.d9tilov.domain.usecases.GetMovieDetails
import com.d9tilov.presentation.entities.Movie

class MovieDetailsVMFactory(
    private val getMovieDetails: GetMovieDetails,
    private val mapper: Mapper<MovieEntity, Movie>
) : ViewModelProvider.Factory {

    var movieId: Long = -1

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailsViewModel(
            getMovieDetails,
            mapper,
            movieId
        ) as T
    }
}
