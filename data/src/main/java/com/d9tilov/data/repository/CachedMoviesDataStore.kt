package com.d9tilov.data.repository

import com.d9tilov.domain.MoviesCache
import com.d9tilov.domain.MoviesDataStore
import com.d9tilov.domain.Optional
import com.d9tilov.domain.entities.MovieEntity
import io.reactivex.Observable

class CachedMoviesDataStore(private val moviesCache: MoviesCache) : MoviesDataStore {

    override fun getMovieById(movieId: Long): Observable<Optional<MovieEntity>> {
        return moviesCache.get(movieId)
    }

    override fun getMovies(): Observable<List<MovieEntity>> {
        return moviesCache.getAll()
    }

}