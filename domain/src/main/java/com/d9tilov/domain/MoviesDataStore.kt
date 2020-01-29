package com.d9tilov.domain

import com.d9tilov.domain.entities.MovieEntity
import io.reactivex.Observable

interface MoviesDataStore {
    fun getMovieById(movieId: Long): Observable<Optional<MovieEntity>>{
        return Observable.just(Optional.of(null))
    }
    fun getMovies(): Observable<List<MovieEntity>>

}
