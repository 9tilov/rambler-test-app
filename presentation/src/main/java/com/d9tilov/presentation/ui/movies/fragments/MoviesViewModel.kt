package com.d9tilov.presentation.ui.movies.fragments

import androidx.lifecycle.MutableLiveData
import com.d9tilov.domain.Mapper
import com.d9tilov.domain.entities.MovieEntity
import com.d9tilov.domain.usecases.GetPopularMovies
import com.d9tilov.presentation.base.BaseViewModel
import com.d9tilov.presentation.common.SingleLiveEvent
import com.d9tilov.presentation.entities.Movie
import javax.inject.Inject

class MoviesViewModel
@Inject constructor(
    private val getPopularMovies: GetPopularMovies,
    private val movieEntityMovieMapper: Mapper<MovieEntity, Movie>
) : BaseViewModel() {
    var viewState = MutableLiveData<PopularMoviesViewState>()
    var errorState = SingleLiveEvent<Throwable>()

    init {
        viewState.value = PopularMoviesViewState()
    }

    fun getMovies() {
        addDisposable(getPopularMovies.observable()
            .flatMap { movieEntityMovieMapper.observable(it) }
            .subscribe({ movies ->
                viewState.value?.let {
                    val newState = this.viewState.value?.copy(showLoading = false, movies = movies)
                    this.viewState.value = newState
                    this.errorState.value = null
                }

            }, {
                viewState.value = viewState.value?.copy(showLoading = false)
                errorState.value = it
            })
        )
    }
}
