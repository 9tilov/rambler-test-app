package com.d9tilov.domain.usecases

import com.d9tilov.domain.MoviesRepository
import com.d9tilov.domain.common.Transformer
import com.d9tilov.domain.entities.MovieEntity
import io.reactivex.Observable

open class GetPopularMovies constructor(
    transformer: Transformer<List<MovieEntity>>,
    private val moviesRepository: MoviesRepository
) :
    UseCase<List<MovieEntity>>(transformer) {
    override fun createObservable(data: Map<String, Any>?): Observable<List<MovieEntity>> {
        return moviesRepository.getMovies()
    }
}