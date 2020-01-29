package com.d9tilov.presentation.ui.movies.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.d9tilov.domain.Mapper
import com.d9tilov.domain.entities.MovieEntity
import com.d9tilov.domain.usecases.GetPopularMovies
import com.d9tilov.presentation.entities.Movie

class PopularMoviesVMFactory(private val useCase: GetPopularMovies,
                             private val mapper: Mapper<MovieEntity, Movie>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoviesViewModel(useCase, mapper) as T
    }

}