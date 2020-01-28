package com.d9tilov.data.db

import com.d9tilov.data.entities.MovieData
import com.d9tilov.domain.Mapper
import com.d9tilov.domain.MoviesCache
import com.d9tilov.domain.Optional
import com.d9tilov.domain.entities.MovieEntity
import io.reactivex.Observable

class RoomFavoritesMovieCache(
    database: MoviesDatabase,
    private val entityToDataMapper: Mapper<MovieEntity, MovieData>,
    private val dataToEntityMapper: Mapper<MovieData, MovieEntity>
) :
    MoviesCache {
    private val dao: MoviesDao = database.getMoviesDao()

    override fun clear() {
        dao.clear()
    }

    override fun save(movieEntity: MovieEntity) {
        dao.saveMovie(entityToDataMapper.mapFrom(movieEntity))
    }

    override fun remove(movieEntity: MovieEntity) {
        dao.removeMovie(entityToDataMapper.mapFrom(movieEntity))
    }

    override fun saveAll(movieEntities: List<MovieEntity>) {
        dao.saveAllMovies(movieEntities.map { entityToDataMapper.mapFrom(it) })
    }

    override fun getAll(): Observable<List<MovieEntity>> {
        return Observable.fromCallable { dao.getFavorites().map { dataToEntityMapper.mapFrom(it) } }
    }

    override fun get(movieId: Long): Observable<Optional<MovieEntity>> {

        return Observable.fromCallable {
            val movieData = dao.get(movieId)
            movieData?.let {
                Optional.of(dataToEntityMapper.mapFrom(it))
            } ?: Optional.empty()
        }
    }

    override fun isEmpty(): Observable<Boolean> {
        return Observable.fromCallable { dao.getFavorites().isEmpty() }
    }
}