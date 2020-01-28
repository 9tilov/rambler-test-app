package com.d9tilov.data.repository

import com.d9tilov.domain.MoviesCache
import com.d9tilov.domain.MoviesDataStore
import com.d9tilov.domain.entities.MovieEntity
import io.reactivex.Observable

class CachedMoviesDataStore(private val moviesCache: MoviesCache) : MoviesDataStore {

    override fun getMovies(): Observable<List<MovieEntity>> {
        return moviesCache.getAll()
    }

}