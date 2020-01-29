package com.d9tilov.data.repository

import com.d9tilov.data.api.Api
import com.d9tilov.data.entities.MovieData
import com.d9tilov.domain.Mapper
import com.d9tilov.domain.MoviesDataStore
import com.d9tilov.domain.entities.MovieEntity
import io.reactivex.Observable

class RemoteMoviesDataStore(
    private val api: Api,
    private val movieDataMapper: Mapper<MovieData, MovieEntity>
) :
    MoviesDataStore {

    override fun getMovies(): Observable<List<MovieEntity>> {
        return api.popularMovies().map { results ->
            results.movies.map {
                movieDataMapper.mapFrom(it)
            }
        }
    }
}
