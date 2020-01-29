package com.d9tilov.domain.usecases

import com.d9tilov.domain.MoviesRepository
import com.d9tilov.domain.Optional
import com.d9tilov.domain.common.Transformer
import com.d9tilov.domain.entities.MovieEntity
import io.reactivex.Observable

class GetMovieDetails(
    transformer: Transformer<Optional<MovieEntity>>,
    private val moviesRepository: MoviesRepository
) : UseCase<Optional<MovieEntity>>(transformer) {

    companion object {
        private const val PARAM_MOVIE_ENTITY = "param:movieEntity"
    }

    fun getById(movieId: Long): Observable<Optional<MovieEntity>> {
        val data = HashMap<String, Long>()
        data[PARAM_MOVIE_ENTITY] = movieId
        return observable(data)
    }

    override fun createObservable(data: Map<String, Any>?): Observable<Optional<MovieEntity>> {
        val movieId = data?.get(PARAM_MOVIE_ENTITY)
        movieId?.let {
            return moviesRepository.getMovie(it as Long)
        } ?: return Observable.error { IllegalArgumentException("MovieId must be provided.") }
    }
}
