package com.d9tilov.data.repository

import com.d9tilov.data.api.Api
import com.d9tilov.data.entities.MovieData
import com.d9tilov.domain.*
import com.d9tilov.domain.entities.MovieEntity
import io.reactivex.Observable

class MoviesRepositoryImpl(api: Api,
                           private val cache: MoviesCache,
                           movieDataMapper: Mapper<MovieData, MovieEntity>) : MoviesRepository {

    private val memoryDataStore: MoviesDataStore
    private val remoteDataStore: MoviesDataStore

    init {
        memoryDataStore = CachedMoviesDataStore(cache)
        remoteDataStore = RemoteMoviesDataStore(api, movieDataMapper)
    }

    override fun getMovies(): Observable<List<MovieEntity>> {
        return memoryDataStore.getMovies().flatMap { t ->
            if (t.isEmpty()) {
                remoteDataStore.getMovies().doOnNext { cache.saveAll(it) }
            } else {
                memoryDataStore.getMovies()
            }
        }
    }

    override fun getMovie(movieId: Long): Observable<Optional<MovieEntity>> {
        return remoteDataStore.getMovieById(movieId)
    }
}
