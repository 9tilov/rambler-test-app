package com.d9tilov.data.repository

import com.d9tilov.data.api.Api
import com.d9tilov.data.entities.DetailsData
import com.d9tilov.data.entities.MovieData
import com.d9tilov.domain.*
import com.d9tilov.domain.entities.MovieEntity
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class MoviesRepositoryImpl(
    api: Api,
    private val cache: MoviesCache,
    private val sharedPreferences: MovieSharedPreferences,
    movieDataMapper: Mapper<MovieData, MovieEntity>,
    detailedDataMapper: Mapper<DetailsData, MovieEntity>
) : MoviesRepository {

    private val memoryDataStore: MoviesDataStore
    private val remoteDataStore: MoviesDataStore

    init {
        memoryDataStore = CachedMoviesDataStore(cache)
        remoteDataStore = RemoteMoviesDataStore(api, movieDataMapper, detailedDataMapper)
    }

    override fun getMovies(): Observable<List<MovieEntity>> {
        return memoryDataStore.getMovies().flatMap { t ->
            if (t.isEmpty() || readyToUpdate()) {
                remoteDataStore.getMovies()
                    .doOnNext {
                        cache.saveAll(it)
                    }
                    .doOnComplete { sharedPreferences.saveLastUpdateTime(System.currentTimeMillis()) }
            } else {
                memoryDataStore.getMovies()
            }
        }
    }

    private fun readyToUpdate(): Boolean {
        val lastUpdateTime = sharedPreferences.loadLastUpdateTime()
        val currentTime = System.currentTimeMillis()
        return (currentTime - lastUpdateTime) >= TimeUnit.HOURS.toMillis(2)
    }

    override fun getMovie(movieId: Long): Observable<Optional<MovieEntity>> {
        return memoryDataStore.getMovieById(movieId)
    }
}
