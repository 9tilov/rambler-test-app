package com.d9tilov.presentation.ui.detail

import androidx.lifecycle.MutableLiveData
import com.d9tilov.domain.Mapper
import com.d9tilov.domain.entities.MovieEntity
import com.d9tilov.domain.usecases.GetMovieDetails
import com.d9tilov.presentation.base.BaseViewModel
import com.d9tilov.presentation.common.SingleLiveEvent
import com.d9tilov.presentation.entities.Movie

class MovieDetailsViewModel(
    private val getMovieDetails: GetMovieDetails,
    private val mapper: Mapper<MovieEntity, Movie>,
    private val movieId: Long
) : BaseViewModel() {
    private lateinit var movieEntity: MovieEntity
    var viewState = MutableLiveData<MovieDetailsViewState>()
    private var favoriteState = MutableLiveData<Boolean>()
    var errorState = SingleLiveEvent<Throwable>()

    init {
        viewState.value = MovieDetailsViewState(isLoading = true)
    }

    fun getMovieDetails() {
        addDisposable(
            getMovieDetails.getById(movieId)
                .map {
                    it.value?.let {
                        movieEntity = it
                        mapper.mapFrom(movieEntity)
                    } ?: run {
                        throw Throwable("Something went wrong :(")
                    }
                }
                .subscribe(
                    {
                        onMovieDetailsReceived(it)
                    },
                    {
                        errorState.value = it
                    }
                )
        )
    }

    private fun onMovieDetailsReceived(movie: Movie) {

        val newViewState = viewState.value?.copy(
            isLoading = false,
            title = movie.originalTitle,
            releaseDate = movie.releaseDate,
            overview = movie.overview,
            backdropUrl = movie.backdropPath
        )

        viewState.value = newViewState
        favoriteState.value = movie.isFavorite
    }
}
